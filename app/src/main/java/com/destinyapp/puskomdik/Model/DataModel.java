package com.destinyapp.puskomdik.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    //Eskull All
    @SerializedName("id_ekskul")
    @Expose
    public String id_ekskul;

    @SerializedName("nama_ekskul")
    @Expose
    public String nama_ekskul;

    @SerializedName("cover_ekskul")
    @Expose
    public String cover_ekskul;

    @SerializedName("pembimbing_ekskul")
    @Expose
    public String pembimbing_ekskul;

    @SerializedName("deskripsi_ekskul")
    @Expose
    public String deskripsi_ekskul;

    @SerializedName("created_at_ekskul")
    @Expose
    public String created_at_ekskul;

    @SerializedName("id_ekskul_foto")
    @Expose
    public String id_ekskul_foto;

    @SerializedName("link_foto")
    @Expose
    public String link_foto;

    //Jadwal Mata Pelajaran
    @SerializedName("id_mapel_kelas_jadwal")
    @Expose
    public String id_mapel_kelas_jadwal;

    @SerializedName("id_mapel_kelas")
    @Expose
    public String id_mapel_kelas;

    @SerializedName("id_mapel")
    @Expose
    public String id_mapel;

    @SerializedName("id_guru")
    @Expose
    public String id_guru;

    @SerializedName("jam_mulai")
    @Expose
    public String jam_mulai;

    @SerializedName("jam_selesai")
    @Expose
    public String jam_selesai;

    @SerializedName("id_kelas")
    @Expose
    public String id_kelas;

    @SerializedName("tgl_kelas")
    @Expose
    public String tgl_kelas;

    @SerializedName("nama_hari")
    @Expose
    public String nama_hari;

    @SerializedName("tgl_tambah_mapel_kelas")
    @Expose
    public String tgl_tambah_mapel_kelas;

    @SerializedName("nama_mapel")
    @Expose
    public String nama_mapel;

    @SerializedName("kode_mapel")
    @Expose
    public String kode_mapel;

    @SerializedName("tgl_tambah_mapel")
    @Expose
    public String tgl_tambah_mapel;

    @SerializedName("created_at_mapel")
    @Expose
    public String created_at_mapel;

    //E-Raport
    @SerializedName("id_raport_siswa")
    @Expose
    public String id_raport_siswa;

    @SerializedName("id_siswa")
    @Expose
    public String id_siswa;

    @SerializedName("nama_raport")
    @Expose
    public String nama_raport;

    @SerializedName("link_file_raport")
    @Expose
    public String link_file_raport;

    @SerializedName("tgl_raport_upload")
    @Expose
    public String tgl_raport_upload;

    //Poin
    @SerializedName("poin")
    @Expose
    public String poin;

    //Poin
    @SerializedName("nama_kelas")
    @Expose
    public String nama_kelas;

    @SerializedName("nama_siswa")
    @Expose
    public String nama_siswa;

    //Agenda
    @SerializedName("id_agenda_sekolah")
    @Expose
    public String id_agenda_sekolah;

    @SerializedName("judul_agenda")
    @Expose
    public String judul_agenda;

    @SerializedName("cover_agenda")
    @Expose
    public String cover_agenda;

    @SerializedName("isi_agenda")
    @Expose
    public String isi_agenda;

    @SerializedName("status_agenda")
    @Expose
    public String status_agenda;

    @SerializedName("created_at_agenda")
    @Expose
    public String created_at_agenda;


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

    public String getId_ekskul() {
        return id_ekskul;
    }

    public void setId_ekskul(String id_ekskul) {
        this.id_ekskul = id_ekskul;
    }

    public String getNama_ekskul() {
        return nama_ekskul;
    }

    public void setNama_ekskul(String nama_ekskul) {
        this.nama_ekskul = nama_ekskul;
    }

    public String getCover_ekskul() {
        return cover_ekskul;
    }

    public void setCover_ekskul(String cover_ekskul) {
        this.cover_ekskul = cover_ekskul;
    }

    public String getPembimbing_ekskul() {
        return pembimbing_ekskul;
    }

    public void setPembimbing_ekskul(String pembimbing_ekskul) {
        this.pembimbing_ekskul = pembimbing_ekskul;
    }

    public String getDeskripsi_ekskul() {
        return deskripsi_ekskul;
    }

    public void setDeskripsi_ekskul(String deskripsi_ekskul) {
        this.deskripsi_ekskul = deskripsi_ekskul;
    }

    public String getCreated_at_ekskul() {
        return created_at_ekskul;
    }

    public void setCreated_at_ekskul(String created_at_ekskul) {
        this.created_at_ekskul = created_at_ekskul;
    }

    public String getId_ekskul_foto() {
        return id_ekskul_foto;
    }

    public void setId_ekskul_foto(String id_ekskul_foto) {
        this.id_ekskul_foto = id_ekskul_foto;
    }

    public String getLink_foto() {
        return link_foto;
    }

    public void setLink_foto(String link_foto) {
        this.link_foto = link_foto;
    }

    public String getId_mapel_kelas_jadwal() {
        return id_mapel_kelas_jadwal;
    }

    public void setId_mapel_kelas_jadwal(String id_mapel_kelas_jadwal) {
        this.id_mapel_kelas_jadwal = id_mapel_kelas_jadwal;
    }

    public String getId_mapel_kelas() {
        return id_mapel_kelas;
    }

    public void setId_mapel_kelas(String id_mapel_kelas) {
        this.id_mapel_kelas = id_mapel_kelas;
    }

    public String getId_mapel() {
        return id_mapel;
    }

    public void setId_mapel(String id_mapel) {
        this.id_mapel = id_mapel;
    }

    public String getId_guru() {
        return id_guru;
    }

    public void setId_guru(String id_guru) {
        this.id_guru = id_guru;
    }

    public String getJam_mulai() {
        return jam_mulai;
    }

    public void setJam_mulai(String jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public String getJam_selesai() {
        return jam_selesai;
    }

    public void setJam_selesai(String jam_selesai) {
        this.jam_selesai = jam_selesai;
    }

    public String getId_kelas() {
        return id_kelas;
    }

    public void setId_kelas(String id_kelas) {
        this.id_kelas = id_kelas;
    }

    public String getTgl_kelas() {
        return tgl_kelas;
    }

    public void setTgl_kelas(String tgl_kelas) {
        this.tgl_kelas = tgl_kelas;
    }

    public String getNama_hari() {
        return nama_hari;
    }

    public void setNama_hari(String nama_hari) {
        this.nama_hari = nama_hari;
    }

    public String getTgl_tambah_mapel_kelas() {
        return tgl_tambah_mapel_kelas;
    }

    public void setTgl_tambah_mapel_kelas(String tgl_tambah_mapel_kelas) {
        this.tgl_tambah_mapel_kelas = tgl_tambah_mapel_kelas;
    }

    public String getNama_mapel() {
        return nama_mapel;
    }

    public void setNama_mapel(String nama_mapel) {
        this.nama_mapel = nama_mapel;
    }

    public String getKode_mapel() {
        return kode_mapel;
    }

    public void setKode_mapel(String kode_mapel) {
        this.kode_mapel = kode_mapel;
    }

    public String getTgl_tambah_mapel() {
        return tgl_tambah_mapel;
    }

    public void setTgl_tambah_mapel(String tgl_tambah_mapel) {
        this.tgl_tambah_mapel = tgl_tambah_mapel;
    }

    public String getCreated_at_mapel() {
        return created_at_mapel;
    }

    public void setCreated_at_mapel(String created_at_mapel) {
        this.created_at_mapel = created_at_mapel;
    }

    public String getId_raport_siswa() {
        return id_raport_siswa;
    }

    public void setId_raport_siswa(String id_raport_siswa) {
        this.id_raport_siswa = id_raport_siswa;
    }

    public String getId_siswa() {
        return id_siswa;
    }

    public void setId_siswa(String id_siswa) {
        this.id_siswa = id_siswa;
    }

    public String getNama_raport() {
        return nama_raport;
    }

    public void setNama_raport(String nama_raport) {
        this.nama_raport = nama_raport;
    }

    public String getLink_file_raport() {
        return link_file_raport;
    }

    public void setLink_file_raport(String link_file_raport) {
        this.link_file_raport = link_file_raport;
    }

    public String getTgl_raport_upload() {
        return tgl_raport_upload;
    }

    public void setTgl_raport_upload(String tgl_raport_upload) {
        this.tgl_raport_upload = tgl_raport_upload;
    }

    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public String getNama_siswa() {
        return nama_siswa;
    }

    public void setNama_siswa(String nama_siswa) {
        this.nama_siswa = nama_siswa;
    }

    public String getId_agenda_sekolah() {
        return id_agenda_sekolah;
    }

    public void setId_agenda_sekolah(String id_agenda_sekolah) {
        this.id_agenda_sekolah = id_agenda_sekolah;
    }

    public String getJudul_agenda() {
        return judul_agenda;
    }

    public void setJudul_agenda(String judul_agenda) {
        this.judul_agenda = judul_agenda;
    }

    public String getCover_agenda() {
        return cover_agenda;
    }

    public void setCover_agenda(String cover_agenda) {
        this.cover_agenda = cover_agenda;
    }

    public String getIsi_agenda() {
        return isi_agenda;
    }

    public void setIsi_agenda(String isi_agenda) {
        this.isi_agenda = isi_agenda;
    }

    public String getStatus_agenda() {
        return status_agenda;
    }

    public void setStatus_agenda(String status_agenda) {
        this.status_agenda = status_agenda;
    }

    public String getCreated_at_agenda() {
        return created_at_agenda;
    }

    public void setCreated_at_agenda(String created_at_agenda) {
        this.created_at_agenda = created_at_agenda;
    }
}
