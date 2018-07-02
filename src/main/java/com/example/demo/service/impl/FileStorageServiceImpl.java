package com.example.demo.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService {
	private final Path rootPath = Paths.get("src/main/resources/static/images");

	@Override
	public void store(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.rootPath.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Resource loadFile(String filename) {
		try {
			Path file = rootPath.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error! -> message = " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootPath.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectory(rootPath);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}

	@Override
	public Stream<Path> loadFiles() {
		try {
			return Files.walk(this.rootPath, 1).filter(path -> !path.equals(this.rootPath))
					.map(this.rootPath::relativize);
		} catch (IOException e) {
			throw new RuntimeException("\"Failed to read stored file");
		}
	}
}
