package com.stacktips.app.service;

import com.stacktips.app.config.FileStorageProperties;
import com.stacktips.app.exception.FileStorageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileStorageServiceTest {

    private FileStorageService fileStorageService;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        FileStorageProperties properties = new FileStorageProperties();
        properties.setUploadDir(tempDir.toString());
        fileStorageService = new FileStorageService(properties);
    }

    @Test
    void testStoreFile() throws Exception {
        MultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpeg", "Test content".getBytes());
        String storedFileName = fileStorageService.storeFile(mockFile);
        Path storedFilePath = tempDir.resolve(storedFileName);
        assertThat(Files.exists(storedFilePath)).isTrue();
        assertThat(new String(Files.readAllBytes(storedFilePath))).isEqualTo("Test content");
    }

    @Test
    void testStoreFileWith_WhenInvalidPath() {
        MultipartFile mockFile = new MockMultipartFile("file", "../test.png", "image/png", "Test content".getBytes());

        assertThatThrownBy(() -> fileStorageService.storeFile(mockFile))
                .isInstanceOf(FileStorageException.class)
                .hasMessageContaining("Could not store file ../test.png. Please try again!");
    }
}
