package io.github.williamledo.icompras.faturamento.api;

import java.io.InputStream;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.github.williamledo.icompras.faturamento.bucket.BucketFile;
import io.github.williamledo.icompras.faturamento.bucket.BucketService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bucket")
@RequiredArgsConstructor
public class BucketController {

	private final BucketService service;
	
	@PostMapping
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		
		try (InputStream is = file.getInputStream() ){
			
			MediaType type = MediaType.parseMediaType(file.getContentType());
			var bucketFile = new BucketFile(file.getOriginalFilename(), is, type,file.getSize());
			
			service.upload(bucketFile);
			
			return ResponseEntity.ok("Arquivo enviado com sucesso!");
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Erro ao fazer upload do arquivo: " + e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<String> getUrl(@RequestParam String filename) {
		
		try {
			String url = service.getUrl(filename);
			return ResponseEntity.ok(url);
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Erro ao obter URL do arquivo: " + e.getMessage());
		}
		
	}
}
