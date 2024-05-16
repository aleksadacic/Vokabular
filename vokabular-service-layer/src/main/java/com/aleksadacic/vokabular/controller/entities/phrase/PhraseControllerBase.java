package com.aleksadacic.vokabular.controller.entities.phrase;

import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import com.aleksadacic.vokabular.vokabulardataimport.export.ReportDocument;
import com.aleksadacic.engine.dataimport.ImportProcedure;
import com.aleksadacic.engine.dataimport.ImportDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.aleksadacic.engine.validations.Validation;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.constraints.NotNull;
import com.aleksadacic.vokabular.business.entities.phrase.PhraseSpecification;
import com.aleksadacic.vokabular.vokabulardataimport.importers.phrase.PhraseImporter;
import com.aleksadacic.vokabular.business.entities.phrase.PhraseManager;
import com.aleksadacic.engine.framework.service.ServiceUtils;
import com.aleksadacic.engine.exceptions.ServiceException;
import com.aleksadacic.engine.exceptions.DataNotFoundException;
import com.aleksadacic.engine.exceptions.BadParametersException;
import com.aleksadacic.engine.datatypes.Id;
import com.aleksadacic.vokabular.business.entities.phrase.Phrase;
import com.aleksadacic.engine.framework.service.EntityController;

abstract class PhraseControllerBase implements EntityController<PhraseDTO, PhraseSearchDTO> {
	private static final Logger logger = Logger.getLogger(PhraseControllerBase.class.getSimpleName());

	@Autowired
	protected PhraseManager manager;
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
	public ResponseEntity<?> insert(PhraseDTO request) {
		try {
			Phrase entity = request.toBusinessEntity();
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
	public ResponseEntity<?> update(PhraseDTO request) {
		try {
			Phrase entity = request.toBusinessEntity();
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
			Optional<Phrase> entity = manager.getById(Id.of(id));
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
	public ResponseEntity<?> search(PhraseSearchDTO request) {
		try {
			if (request.getPageNumber() == null || request.getPageSize() == null) {
				throw new BadParametersException("pageNumber", "pageSize");
			}
			PhraseSpecification specification = request.buildSpecification();
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
	public ResponseEntity<?> count(PhraseSearchDTO request) {
		try {
			PhraseSpecification specification = request.buildSpecification();
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
	public ResponseEntity<?> getData(PhraseSearchDTO request) {
		try {
			PhraseSpecification specification = request.buildSpecification();
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
			PhraseImporter importer = new PhraseImporter(manager, request.getFile().getInputStream());
			ImportProcedure procedure = ImportProcedure.of(request, importer);
			procedure.execute();
			logger.log(Level.INFO, "importData: finished importing data.");
			return ResponseEntity.ok(procedure.getReport().toJson());
		} catch (Exception e) {
			return ServiceUtils.errorResponse(e);
		}
	}

	public ResponseEntity<byte[]> importDataWithReport(ImportDTO request) {
		try {
			logger.log(Level.INFO, "importDataWithReport: {0}", request);
			Validation.validate(request);
			PhraseImporter importer = new PhraseImporter(manager, request.getFile().getInputStream());
			ImportProcedure procedure = ImportProcedure.of(request, importer);
			procedure.execute();
			logger.log(Level.INFO, "importDataWithReport: finished importing data.");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("report", "report.pdf");
			byte[] data = ReportDocument.of(procedure.getReport());
			return ResponseEntity.ok().headers(headers).contentLength(data.length).body(data);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new byte[0]);
		}
	}
}
