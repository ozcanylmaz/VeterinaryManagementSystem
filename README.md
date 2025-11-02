# 🐾 Veterinary Management System

Bu proje, veteriner kliniklerinin temel operasyonlarını yönetmek amacıyla geliştirilmiş bir **Spring Boot** uygulamasıdır.  
Hayvan (pet), sahip (owner), randevu (appointment) ve muayene (vaccine) gibi verilerin yönetimini sağlar.

---

## 📘 İçindekiler
- [Genel Bakış](#genel-bakış)
- [Özellikler](#özellikler)
- [Teknolojiler](#teknolojiler)
- [Kurulum](#kurulum)
- [Çalıştırma](#çalıştırma)
- [Konfigürasyon](#konfigürasyon)
- [API Uç Noktaları](#api-uç-noktaları)
- [Postman Koleksiyonu ve Diyagramlar](#postman-koleksiyonu-ve-diyagramlar)
- [Katkıda Bulunma](#katkıda-bulunma)
- [Lisans](#lisans)

---

## 🧩 Genel Bakış

Veterinary Management System, veteriner kliniklerinde kayıt ve randevu işlemlerini kolaylaştırmak için oluşturulmuş bir örnek yönetim sistemidir.  
Proje **katmanlı mimari (Controller → Service → Repository)** prensiplerine göre yapılandırılmıştır.

Bu proje hem **Spring Boot öğrenmek**, hem de **RESTful API** geliştirme pratiği yapmak isteyen geliştiriciler için örnek bir uygulamadır.

---

## 🚀 Özellikler

- 🐶 **Hayvan (Pet)** CRUD işlemleri  
- 👨‍⚕️ **Sahip (Owner)** CRUD işlemleri  
- 📅 **Randevu (Appointment)** planlama, listeleme ve silme işlemleri  
- 💉 **Aşı (Vaccine)** kayıt yönetimi  
- 🔍 ID veya tarih bazlı sorgulama  
- ⚙️ Katmanlı mimari: Controller, Service, Repository  
- 🧪 Postman koleksiyonu ile API test desteği  

---

## 🛠️ Teknolojiler

| Teknoloji | Açıklama |
|------------|-----------|
| **Java 17+** | Programlama dili |
| **Spring Boot** | Uygulama çatısı |
| **Spring Web** | REST API oluşturma |
| **Spring Data JPA** | ORM katmanı |
| **Maven** | Proje yapı yöneticisi |
| **H2 / MySQL** | Veritabanı (config'e göre değişebilir) |

---

## ⚙️ Kurulum

1. Repoyu klonla:
   ```bash
   git clone https://github.com/ozcanylmaz/VeterinaryManagementSystem.git
   cd VeterinaryManagementSystem
