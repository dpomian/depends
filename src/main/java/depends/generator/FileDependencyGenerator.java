/*
MIT License

Copyright (c) 2018-2019 Gang ZHANG

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package depends.generator;

import java.util.Iterator;
import java.util.List;

import depends.entity.CandidateTypes;
import depends.entity.Entity;
import depends.entity.FileEntity;
import depends.entity.TypeEntity;
import depends.entity.repo.EntityRepo;
import depends.matrix.core.DependencyDetail;
import depends.matrix.core.DependencyMatrix;
import depends.relations.Relation;

public class FileDependencyGenerator extends DependencyGenerator {
	/**
	 * Build the dependency matrix (without re-mapping file id)
	 * 
	 * @param repo which contains entities and relations
	 * @return the generated dependency matrix
	 */
	@Override
	public DependencyMatrix build(EntityRepo repo, List<String> typeFilter) {
		System.out.println("Creating dependency matrix....");
		DependencyMatrix matrix = new DependencyMatrix(typeFilter);
		Iterator<Entity> iterator = repo.entityIterator();

		while (iterator.hasNext()) {
			Entity entity = iterator.next();

			// Don't add out-of-scope entities (when does this happen?)
			if (!entity.inScope()) {
				continue;
			}

			// Our top level relationships are between files
			// So add a node if this entity is a file
			if (entity instanceof FileEntity) {
				String name = stripper.stripFilename(entity.getDisplayName());
				name = filenameWritter.reWrite(name);
				matrix.addNode(name, entity.getId());
			}

			// Get the file ID of our entity
			int fileEntityFrom = getFileEntityIdNoException(entity);

			// Skip if the file is null or out-of-scope (when does this happen?)
			if (fileEntityFrom == -1) {
				continue;
			}

			for (Relation relation : entity.getRelations()) {
				Entity relatedEntity = relation.getEntity();

				// Skip related entity when its null (when does this happen?)
				if (relatedEntity == null) {
					continue;
				}

				// If our relatedEntity is not a CandidateTypes, add the dependency as normal
				if (!(relatedEntity instanceof CandidateTypes)) {
					addDependency(matrix, relation, fileEntityFrom, entity, relatedEntity);
					continue;
				}

				// Otherwise, add dependencies to entities inside the CandidateTypes
				List<TypeEntity> candidateTypes = ((CandidateTypes) relatedEntity).getCandidateTypes();
				for (TypeEntity candidateType : candidateTypes) {
					addDependency(matrix, relation, fileEntityFrom, entity, candidateType);
				}
			}
		}

		System.out.println("Finished creating dependency matrix.");
		return matrix;
	}

	private void addDependency(DependencyMatrix matrix, Relation relation, int fileEntityFrom, Entity from, Entity to) {
		// Skip if null (when does this happen?)
		if (to.getId() < 0) {
			return;
		}

		int fileEntityTo = getFileEntityIdNoException(to);

		if (fileEntityTo != -1) {
			DependencyDetail detail = buildDescription(from, to);
			matrix.addDependency(relation.getType(), fileEntityFrom, fileEntityTo, 1, detail);
		}
	}

	private int getFileEntityIdNoException(Entity entity) {
		Entity ancestor = entity.getAncestorOfType(FileEntity.class);

		if (ancestor == null || !ancestor.inScope()) {
			return -1;
		}

		return ancestor.getId();
	}

}
