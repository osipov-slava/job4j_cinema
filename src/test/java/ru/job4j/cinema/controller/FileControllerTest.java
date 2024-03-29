package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cinema.dto.FileDto;
import ru.job4j.cinema.service.FileService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileControllerTest {

    private FileService fileService;

    private FileController fileController;

    private MultipartFile testFile;

    @BeforeEach
    public void initServices() {
        fileService = mock(FileService.class);
        fileController = new FileController(fileService);
        testFile = new MockMultipartFile("testFile.img", new byte[]{1, 2, 3});
    }

    @Test
    public void whenRequestFileByIdThenReturnResponseEntity() throws Exception {
        var fileDto = new FileDto(testFile.getOriginalFilename(), testFile.getBytes());
        var optionalFile = Optional.of(fileDto);
        when(fileService.getFileById(1)).thenReturn(optionalFile);

        var responseEntity = fileController.getById(1);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(testFile.getBytes());
    }

    @Test
    public void whenRequestUnknownFileByIdThenReturnResponseNotFound() throws Exception {
        when(fileService.getFileById(anyInt())).thenReturn(Optional.empty());

        var responseEntity = fileController.getById(1);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isEqualTo(null);
    }
}
