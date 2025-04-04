package com.clava1096.musicstreaming.services.s3;

import com.clava1096.musicstreaming.models.Track;
import com.clava1096.musicstreaming.models.dto.TrackDTO;
import com.clava1096.musicstreaming.models.repositories.TrackRepository;
import com.clava1096.musicstreaming.utils.ProjectConstants;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class S3Service {

    private final MinioClient minioClient;

    private final TrackRepository trackRepository;

    public void uploadTrack(UUID trackId , String bucket, MultipartFile file)
            throws MinioException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        Track track = trackRepository.findById(trackId)
                .orElseThrow(() -> new RuntimeException("Track not found"));
        String fileKey = track.getGenre().getId() + "/" + track.getAuthor() + "/" + track.getAlbum().getId() + "/" + track.getId();
        try(InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(fileKey)
                        .stream(inputStream , file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());
        }
        track.setFilePath(fileKey);
        trackRepository.save(track);
    }

    public InputStream downloadFile(String bucket, String object)
            throws MinioException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucket)
                        .object(object)
                        .build());
    }

    public void deleteFile(String bucket, String object)
            throws MinioException, NoSuchAlgorithmException, InvalidKeyException, IOException{
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucket)
                        .object(object)
                        .build());
    }


    public String generateTrackUrl(UUID trackId)
            throws MinioException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        Track track = trackRepository.findById(trackId)
                .orElseThrow(() -> new RuntimeException("Track not found"));


        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.PUT)
                        .bucket(ProjectConstants.DEFAULT_BUCKET)
                        .object(track.getFilePath())
                        .expiry(ProjectConstants.DEFAULT_TIME_UPLOAD_TRACK)
                        .build()
        );
    }

}
