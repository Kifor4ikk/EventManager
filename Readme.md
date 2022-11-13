Event manager project
===
This project is REST service for event management and organization.

Technologies
---
* Spring boot
* Hibernate
* PostgreSQL
* TestNG

Model describe
---
Event
* String Theme - in other words name of event. Size: 3 - 70 symbols.
* String describe - Size: 30 - 10000 symbols.
* String manager - Main initiator of event. Size: 3 - 70 symbols.
* LocalDateTime dateTime - Date and time of event. Can be only in future. 

Entity describe
---
EventEntity
* String Theme - in other words name of event. Size: 3 - 70 symbols.
* String describe - Size: 30 - 10000 symbols.
* String manager - Main initiator of event. Size: 3 - 70 symbols.
* Date date - Date of event. Can be only in future.
* Time time - Time of event.
* Date creationDate - Date creation of this note. (Auto setter)
* Time creationTime - Time creation of this note. (Auto setter)
* boolean isDeleted - For soft delete. If entity marked as deleted you can do nothing with it

Dao describe
---
* String Theme - in other words name of event. Size: 3 - 70 symbols.
* String describe - Size: 30 - 10000 symbols.
* String manager - Main initiator of event. Size: 3 - 70 symbols.
* Date date - Date of event. Can be only in future. 
* String time - Time of event. "HH:MM:SS" - will be converted to time

Opportunities
---
* CRUD for Event
* soft delete
* getAll() function with pagination and filters.

 How to start?
---
+ Step 1. Download archive Start.rar (https://disk.yandex.ru/d/fLLyeK-vjnZbGw) too large for git
+ Step 2. Unzip in any folder(Not bucket).
+ Step 3. Change server address and port in application.yml file.
+ Step 4. Change database properties in hibernate.cfg.xml file.
+ Step 5. Run Start.bat if you use windows or java -cp %fileName% ru.kifor4ik.Starter
+ Step 6. Enjoy!

About API functions
---
+ create
  + params for create event.
    + Theme - String(3-30)
    + Describe - String(30-10000)
    + Manager - String(3-70)
    + Date - String(yyyy-mm-dd) example "2024-12-12"
    + Time - String(hh:mm:ss) example "12:12:12"
+ getById()
  + params for get event by id.
    + id - Long(0 - ?)
+ update 
  + params for update event.
    + id - Long(0 - ?)
    + Another params are similar for create
+ softDelete
  + params for soft delete
    + id - Long(0 - ?)
+ delete
  + params for delete
    + id - Long(0 - ?)
+ getAll
  + params for getAll
    + pageSize - int(1-?)
    + page - int(1-?)
    + Theme - String(3-30)
    + Describe - String(30-10000)
    + Date - String(yyyy-mm-dd) example "2024-12-12"
    + Time - String(hh:mm:ss) example "12:12:12"
  + If you dont need filter by any of this param just send null instead of this.
  + If you will send null instead of page or pageSize this variables will be set automatically (1 and 10)
