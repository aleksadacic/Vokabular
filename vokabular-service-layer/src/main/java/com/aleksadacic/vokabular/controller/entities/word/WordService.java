package com.aleksadacic.vokabular.controller.entities.word;

import com.aleksadacic.engine.dataimport.ImportDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

@RestController
@RequestMapping("/api/word")
public class WordService extends WordController {
	@Override
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create() {
		return super.create();
	}
	@Override
	@PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> insert(@RequestBody WordDTO request) {
		return super.insert(request);
	}
	@Override
	@PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody WordDTO request) {
		return super.update(request);
	}
	@Override
	@PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@RequestBody String id) {
		return super.delete(id);
	}
	@Override
	@PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> search(@RequestBody WordSearchDTO request) {
		return super.search(request);
	}
	@Override
	@PostMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getById(@RequestBody @NonNull String id) {
		return super.getById(id);
	}
	@Override
	@PostMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> count(WordSearchDTO request) {
		return super.count(request);
	}
	@Override
	@PostMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAll() {
		return super.getAll();
	}
	@Override
	@PostMapping(value = "/getData", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getData(WordSearchDTO request) {
		return super.getData(request);
	}
	@Override
	@PostMapping(value = "/importData", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> importData(ImportDTO request) {
		return super.importData(request);
	}
}
