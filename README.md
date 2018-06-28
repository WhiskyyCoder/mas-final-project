# mas-final-project

Scenariusz przypadku użycia

Nazwa przypadku użycia:	Złóż rezerwację
Warunek początkowy	W systemie jest zarejestrowany przynajmniej jeden właściciel kwatery, który posiada przynajmniej jedną kwaterę z pokojami.
Główny przepływ zdarzeń	
1. Aktor właściciel kwatery uruchamia przypadek użycia.
2. Aktor przechodzi do zakładki kwatery.
3. System wyswietla listę kwater    danego właściciela, a następnie aktor wybiera kwaterę.
4. System wyswietla formularz zawierający daty planowanej rezerwacji, a następnie aktor wprowadza daty.
5. System wyświetla dostępne pokoje w zadanym terminie. Aktor zaznacza pokoje.
6. System wyświetla formularz odpytując o nazwisko rezerwującego, telefon kontaktowy, ilość osób oraz cenę za osobę/dobę.
7. System wyświetla podsumowanie rezerwacji.

Alternatywny przepływ zdarzeń	
2a. Aktor nie przechodzi do zakładki kwatery, kończony jest przypadek użycia.
3a. Aktor nie dokonuje wyboru kwatery, kończony jest przypadek użycia.
4a. Aktor wprowadza zły format danych, system informuje o tym i wraca do punktu 3.
4b. Aktor podaje niepoprawny zakres dat, system informuje o tym i wraca do punktu 3.
5a. System informuje o braku dostepności pokoi w zadanym terminie i wraca do punktu 4.
5b. Aktor nie dokonał wyboru, kończony jest przypadek użycia.
6a. Aktor wprowadza zły format danych, system informuje o tym i wraca do punktu 6.
7a. System wyświetla informacje o błędzie i kończy przypadek użycia.
Zakończenie:	W dowolnym momecie

Warunek końcowy:	Wprowadzono nową rezerwację do  systemu.
