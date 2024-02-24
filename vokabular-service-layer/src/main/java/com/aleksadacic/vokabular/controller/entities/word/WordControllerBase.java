package com.aleksadacic.vokabular.controller.entities.word;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.constraints.NotNull;
import com.aleksadacic.vokabular.business.entities.word.WordSpecification;
import com.aleksadacic.vokabular.business.entities.word.WordManager;
import com.aleksadacic.engine.framework.service.ServiceUtils;
import com.aleksadacic.engine.exceptions.ServiceException;
import com.aleksadacic.engine.exceptions.DataNotFoundException;
import com.aleksadacic.engine.exceptions.BadParametersException;
import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.vokabular.business.entities.word.Word;
import com.aleksadacic.engine.framework.service.EntityController;

abstract class WordControllerBase implements EntityController<WordDTO, WordSearchDTO> {
	@Autowired
	protected WordManager manager;
	@Override
	public ResponseEntity<?> create() {
		try {
			return ResponseEntity.ok(manager.create());
		} catch (Exception e) {
			e.printStackTrace();
			return ServiceUtils.errorResponse(e);
		}
	}
	@Override
	public ResponseEntity<?> insert(WordDTO request) {
		try {
			Word entity = request.convertToBusinessEntity();
			if (entity == null) {
				throw new BadParametersException();
			}
			return ResponseEntity.ok(manager.insert(entity));
		} catch (Exception e) {
			e.printStackTrace();
			return ServiceUtils.errorResponse(e);
		}
	}
	@Override
	public ResponseEntity<?> update(WordDTO request) {
		try {
			Word entity = request.convertToBusinessEntity();
			if (entity == null) {
				throw new ServiceException();
			}
			return ResponseEntity.ok(manager.update(entity));
		} catch (Exception e) {
			e.printStackTrace();
			return ServiceUtils.errorResponse(e);
		}
	}
	@Override
	public ResponseEntity<?> delete(@NotNull String id) {
		try {
			Optional<Word> entity = manager.getById(Id.of(id));
			if (entity.isEmpty()) {
				throw new DataNotFoundException();
			}
			manager.delete(entity.get());
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ServiceUtils.errorResponse(e);
		}
	}
	@Override
	public ResponseEntity<?> search(WordSearchDTO request) {
		try {
			if (request.getPageNumber() == null || request.getPageSize() == null) {
				throw new BadParametersException("pageNumber", "pageSize");
			}
			WordSpecification specification = request.buildSpecification();
			if (request.getSort() != null && !request.getSort().isEmpty()) {
				manager.setSort(Sort.by((String[]) request.getSort().toArray()));
			}
			return ResponseEntity.ok(manager.getPageData(specification));
		} catch (Exception e) {
			e.printStackTrace();
			return ServiceUtils.errorResponse(e);
		}
	}
	@Override
	public ResponseEntity<?> getById(@NotNull String id) {
		try {
			return ResponseEntity.ok(manager.getById(Id.of(id)));
		} catch (Exception e) {
			e.printStackTrace();
			return ServiceUtils.errorResponse(e);
		}
	}
	@Override
	public ResponseEntity<?> count(WordSearchDTO request) {
		try {
			WordSpecification specification = request.buildSpecification();
			return ResponseEntity.ok(manager.count(specification));
		} catch (Exception e) {
			e.printStackTrace();
			return ServiceUtils.errorResponse(e);
		}
	}
	@Override
	public ResponseEntity<?> getAll() {
		try {
			return ResponseEntity.ok(manager.getData());
		} catch (Exception e) {
			e.printStackTrace();
			return ServiceUtils.errorResponse(e);
		}
	}
	@Override
	public ResponseEntity<?> getData(WordSearchDTO request) {
		try {
			WordSpecification specification = request.buildSpecification();
			if (request.getPageNumber() != null && request.getPageSize() != null) {
				manager.setPageable(request.getPageNumber(), request.getPageSize());
			}
			if (request.getSort() != null && !request.getSort().isEmpty()) {
				manager.setSort(Sort.by((String[]) request.getSort().toArray()));
			}
			return ResponseEntity.ok(manager.getData(specification));
		} catch (Exception e) {
			e.printStackTrace();
			return ServiceUtils.errorResponse(e);
		}
	}
}
