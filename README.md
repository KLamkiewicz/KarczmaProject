KarczmaProject
==============

Simple system to manage RPG sessions. Creating in polish.

==============

!!! UWAGA !!!
Plik hibernate.cfg.xml został usunięty z repozytorium, aby uniknąć
nieuprawnionego dostępu do bazy danych, na której projekt jest rozwijany.
Aby korzystać z programu należy utworzyć powyższy plik ze standardowymi
ustawieniami oraz własnymi danymi dostępowymi do bazy danych.

Dodatkowo należy zmapować wykorzystywane klasy encyjne:
  <mapping class="pl.karczma.entities.SesjaEntity"/>
  <mapping class="pl.karczma.entities.UzytkownikEntity"/>
  <mapping class="pl.karczma.entities.UserAuthEntity"/>
  
Baza domyślnie wykorzystuje mySQL i działa na dialecie: org.hibernate.dialect.MySQL5InnoDBDialect
i pod kątem takich ustawień kod był testowany.

==============

Translation will be delivered later (probably when polish version will
work with all planned funcionality).
