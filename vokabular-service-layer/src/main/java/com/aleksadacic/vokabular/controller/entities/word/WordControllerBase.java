package com.aleksadacic.vokabular.controller.entities.word;

import com.aleksadacic.engine.dataimport.ImportDTO;
import com.aleksadacic.engine.dataimport.ImportProcedure;
import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.engine.exceptions.BadParametersException;
import com.aleksadacic.engine.exceptions.DataNotFoundException;
import com.aleksadacic.engine.exceptions.ServiceException;
import com.aleksadacic.engine.framework.service.EntityController;
import com.aleksadacic.engine.framework.service.ServiceUtils;
import com.aleksadacic.engine.validations.Validation;
import com.aleksadacic.vokabular.business.entities.word.Word;
import com.aleksadacic.vokabular.business.entities.word.WordManager;
import com.aleksadacic.vokabular.business.entities.word.WordSpecification;
import com.aleksadacic.vokabular.vokabulardataimport.importers.WordImporter;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class WordControllerBase implements EntityController<WordDTO, WordSearchDTO> {
    private static final Logger logger = Logger.getLogger(WordControllerBase.class.getSimpleName());

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
            Validation.validate(request);
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
            Validation.validate(request);
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
                manager.setSort(Sort.by(request.getSort().toArray(new String[0])));
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
                manager.setSort(Sort.by(request.getSort().toArray(new String[0])));
            }
            return ResponseEntity.ok(manager.getData(specification));
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceUtils.errorResponse(e);
        }
    }

    public ResponseEntity<?> importData(ImportDTO request) {
        try {
            logger.log(Level.INFO, "importData: {0}", request);
            Validation.validate(request);
            WordImporter importer = new WordImporter(manager, request.getFile().getInputStream());
            ImportProcedure procedure = ImportProcedure.of(request, importer);
            procedure.execute();
            logger.log(Level.INFO, "importData: finished importing data.");
            return ResponseEntity.ok(procedure.getReport().toJson());
        } catch (Exception e) {
            return ServiceUtils.errorResponse(e);
        }
    }
}
