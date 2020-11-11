package com.destinyapp.puskomdik.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel {
    @SerializedName("accessToken")
    @Expose
    public String accessToken;

    @SerializedName("name")
    @Expose
    public String name;
    
    @SerializedName("usernameUser")
    @Expose
    public String usernameUser;

    @SerializedName("as")
    @Expose
    public String as;

    @SerializedName("photo")
    @Expose
    public String photo;

    //Kabar Sekolah
    @SerializedName("id_kabar_sekolah")
    @Expose
    public String id_kabar_sekolah;

    @SerializedName("id_sekolah")
    @Expose
    public String id_sekolah;

    @SerializedName("judul_kabar")
    @Expose
    public String judul_kabar;

    @SerializedName("cover_kabar")
    @Expose
    public String cover_kabar;

    @SerializedName("isi_kabar")
    @Expose
    public String isi_kabar;

    @SerializedName("status_kabar")
    @Expose
    public String status_kabar;

    @SerializedName("created_at_kabar")
    @Expose
    public String created_at_kabar;

    //Struktur Organisasi
    @SerializedName("id_struktur_org")
    @Expose
    public String id_struktur_org;

    @SerializedName("nama_guru")
    @Expose
    public String nama_guru;

    @SerializedName("jabatan")
    @Expose
    public String jabatan;

    @SerializedName("file_foto_struktur")
    @Expose
    public String file_foto_struktur;

    @SerializedName("sort_num")
    @Expose
    public String sort_num;

    @SerializedName("created_at")
    @Expose
    public String created_at;

    //Prestasi
    @SerializedName("id_prestasi")
    @Expose
    public String id_prestasi;

    @SerializedName("judul_prestasi")
    @Expose
    public String judul_prestasi;

    @SerializedName("deskripsi_prestasi")
    @Expose
    public String deskripsi_prestasi;

    @SerializedName("tgl_prestasi")
    @Expose
    public String tgl_prestasi;

    @SerializedName("foto_prestasi")
    @Expose
    public String foto_prestasi;

    @SerializedName("created_at_prestasi")
    @Expose
    public String created_at_prestasi;

    //GETTER SETTER

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUsernameUser() {
        return usernameUser;
    }

    public void setUsernameUser(String usernameUser) {
        this.usernameUser = usernameUser;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId_kabar_sekolah() {
        return id_kabar_sekolah;
    }

    public void setId_kabar_sekolah(String id_kabar_sekolah) {
        this.id_kabar_sekolah = id_kabar_sekolah;
    }

    public String getId_sekolah() {
        return id_sekolah;
    }

    public void setId_sekolah(String id_sekolah) {
        this.id_sekolah = id_sekolah;
    }

    public String getCover_kabar() {
        return cover_kabar;
    }

    public void setCover_kabar(String cover_kabar) {
        this.cover_kabar = cover_kabar;
    }

    public String getIsi_kabar() {
        return isi_kabar;
    }

    public void setIsi_kabar(String isi_kabar) {
        this.isi_kabar = isi_kabar;
    }

    public String getStatus_kabar() {
        return status_kabar;
    }

    public void setStatus_kabar(String status_kabar) {
        this.status_kabar = status_kabar;
    }

    public String getCreated_at_kabar() {
        return created_at_kabar;
    }

    public void setCreated_at_kabar(String created_at_kabar) {
        this.created_at_kabar = created_at_kabar;
    }

    public String getId_struktur_org() {
        return id_struktur_org;
    }

    public void setId_struktur_org(String id_struktur_org) {
        this.id_struktur_org = id_struktur_org;
    }

    public String getNama_guru() {
        return nama_guru;
    }

    public void setNama_guru(String nama_guru) {
        this.nama_guru = nama_guru;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getFile_foto_struktur() {
        return file_foto_struktur;
    }

    public void setFile_foto_struktur(String file_foto_struktur) {
        this.file_foto_struktur = file_foto_struktur;
    }

    public String getSort_num() {
        return sort_num;
    }

    public void setSort_num(String sort_num) {
        this.sort_num = sort_num;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId_prestasi() {
        return id_prestasi;
    }

    public void setId_prestasi(String id_prestasi) {
        this.id_prestasi = id_prestasi;
    }

    public String getJudul_prestasi() {
        return judul_prestasi;
    }

    public void setJudul_prestasi(String judul_prestasi) {
        this.judul_prestasi = judul_prestasi;
    }

    public String getDeskripsi_prestasi() {
        return deskripsi_prestasi;
    }

    public void setDeskripsi_prestasi(String deskripsi_prestasi) {
        this.deskripsi_prestasi = deskripsi_prestasi;
    }

    public String getTgl_prestasi() {
        return tgl_prestasi;
    }

    public void setTgl_prestasi(String tgl_prestasi) {
        this.tgl_prestasi = tgl_prestasi;
    }

    public String getFoto_prestasi() {
        return foto_prestasi;
    }

    public void setFoto_prestasi(String foto_prestasi) {
        this.foto_prestasi = foto_prestasi;
    }

    public String getCreated_at_prestasi() {
        return created_at_prestasi;
    }

    public void setCreated_at_prestasi(String created_at_prestasi) {
        this.created_at_prestasi = created_at_prestasi;
    }

    public String getJudul_kabar() {
        return judul_kabar;
    }

    public void setJudul_kabar(String judul_kabar) {
        this.judul_kabar = judul_kabar;
    }
}
