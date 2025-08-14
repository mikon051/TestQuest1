package org.example;

import jakarta.persistence.*;

import java.util.Arrays;

@jakarta.persistence.Entity
@Table(name = "employees")
public class UserEntity {

    @GeneratedValue
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob // Указывает, что это поле для хранения больших объемов данных
    @Column(name = "photo", columnDefinition = "LONGBLOB")
    private byte[] photo; // Массив байтов, представляющий фотографию

    @Column(name = "photo_file_name")
    private String photoFileName;

    @Column(name = "photo_content_type")
    private String photoContentType;

    @Column(name = "lastname")
    private String lastname;

    public UserEntity() {
    }

    public UserEntity(UserDTO userDTO) {
        this.name = userDTO.getName();
        this.lastname = userDTO.getLastname();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", photoFileName='" + photoFileName + '\'' +
                ", photoContentType='" + photoContentType + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
