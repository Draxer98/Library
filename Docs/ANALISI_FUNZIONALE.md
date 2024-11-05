# ANALISI FUNZIONALE

## LOGIN

All'avvio l'utente si trova davanti un menu:

[1] - Accedi <br>
[2] - Esci (esce dal programma)

### Accedi

Se l'utente sceglie l'opzione 1 allora verrà chiesto di inserire:

- id
- password

Se sbaglia l'id o la password, verrà chiesto di reinserire entrambe.

---

## CICLO PRINCIPALE PER UTENTI

Dopo aver fatto l'accesso, il programma stampa un menu con queste scelte:

### 1. Comprare un libro

Dopo aver scelto questa opzione si apre un menu:

- **Inserire informazioni del libro da comprare** <br>
  L'utente dovrà inserire l'ISBN del libro che vuole comprare.
  Se il programma riesce a trovare il libro, stamperà il prezzo a schermo e chiederà all'utente se lo vuole comprare. <br>
  Se l'utente non vuole comprarlo, digiterà n e il programma tornerà al menu per comprare un libro. <br>
  Se l'utente vuole comprarlo, digiterà y e il programma controllerà il saldo dell'utente. <br>
  Se il saldo è inferiore al prezzo del libro, il programma tornerà al menu per comprare un libro. <br>
  Se il saldo è accettabile, l'operazione verrà effettuata e il programma tornerà al menu per comprare un libro. <br>

- **Visualizzare libri disponibili** <br>
  Il programma stampa tutti i libri disponibili per l'acquisto, mostrando a lato la quantità disponibile.

- **Esci** Torna al menu precedente.

### 2. Prendere in prestito un libro

Dopo aver scelto questa opzione si apre un menu:

- **Inserire informazioni del libro da prendere in prestito**
  - L'utente dovrà inserire l'ISBN del libro che vuole prendere in prestito.
  - Se il programma non trova il libro, si interrompe con un messaggio di errore e torna al menu precedente.

- **Visualizzare libri disponibili**
  - Il programma visualizza la lista di tutti i libri disponibili.

- **Esci**
  - Torna al menu precedente.

---

### 3. Estendere la data di consegna di un libro

---

### 4. Info account

Dopo aver scelto questa opzione si apre un menu:

- **Visualizza impostazioni** <br>
  Il programma stampa le informazioni dell'account.
- **Resetta password** <br>
  Il programma stampa dei numeri da chiamare se si vuole resettare la password.

### 6. Esci
