# 1. Analyse fonctionnelle :

## 1.1)  Concepts métiers (les entités)
   - Client
   - Enchere
   - OffreManuelle
   - OffreAuto

## 1.2)  Les règles métier

**Enchère manuelle:**<br>
    1. Une enchère possède une date de début , une date de fin, un montant initial, un montant courant et un statut (active, finie).<br>
    2. Un client peut déposer une ou plusieurs offres sur une enchère active.<br>
    3. Une offre doit être supérieure à l’offre actuellement gagnante.<br>


**Enchère automatique:**<br>
1. Un client A sélectionne une enchère active dans le système et définit un montant maximal.<br>
2. Le système enregistre l’offre automatique avec le max défini. Le prix courant de l’enchere reste le même montant_0.<br>
3. Pour la même enchere, un autre client B mise un montant₁ > montant_0 <br>
2. Le système surenchérit automatiquement pour le client A avec un montant₂ = montant₁ ++ <br> 
3. Le système ne doit pas dépasser le maximum défini. <br>

# 2. Le modèle relationnel (UML)
   Client (1) ---- (n) OffreManuelle (n) ---- (1) Enchere

   Client (1) ---- (n) OffreAutomatique  (n) ---- (1) Enchere


# 3. Services métier

# - **ClientService**
*trouverClients()*: renvoie une liste des clients actifs dans le système.<br>


# - **EnchereService**
   *trouverEncheresActives()*: renvoie une liste d’enchères.<br>
   *trouverEnchereParId()*: renvoie une enchère par son identifiant.

# - **OffreManuelleService**
*deposerOffre(clientId, enchereId, montant)* : le client soumet une offre avec un montant pour une enchère donnée.<br>
**Règles :**

- L’enchère doit être active.
- La date fin de l’enchère doit être postérieure à la date du jour.
- Montant > montant courant de l’enchère > 0.


# - **OffreAutomatiqueService**
*creerOffre(clientId, enchereId, montant)* : le client configure une offre avec un montant maximum.

**Règles :**
- L’enchère doit être active.
- Le montant > 0.

<u>Algorithme de surenchère automatique :</u>
1. le client A récupére l’enchère active et définit un montant MAX.
2. le système vérifie si le montant de l’offre automatique est supérieur au montant courant de l’enchère.
3. Si oui, le système encherit automatiquement les autre clients au nom du client A, en augmentant le montant courant de l’enchère jusqu’à atteindre le montant maximum défini par le client A.
4. Si le montant de l’offre automatique atteint le montant maximum, le système arrête de surenchérir pour ce client.

# 4. Le Front Angular :
1. Écran qui liste les enchères actives du système.

   **ACTIONS**: voir détail d’une enchère <br>
   **ROUTE**: /encheres <br>

2. Écran de détail d’une enchère (description, montant actuel, temps restant,  historique des offres).<br>
   **ACTIONS**: surenchérir, configurer auto-enchere, quitter <br>
   **ROUTE**: /encheres/:id <br>

3. Popup de surenchere: pour saisir le montant de l’offre<br>
   **ACTIONS**: valider, quitter<br>

4. Popup de configuration d’une enchere automatique : pour saisie du montant maximal.<br>
   **ACTIONS**: enregistrer, quitter<br>

5. Pour simuler un écran de connexion, j'ajoute un combobox qui liste les clients existants, le client sélectionné dans le combo box est considéré comme connecté.
   **ACTIONS**: séléctionner un client dans le combobox<br>

# 5 . Instructions de lancement du projet :
pour lancer l'application des enchères, suivez les étapes ci-dessous :
- lancer le backend (déploiment de l'api REST): application spring boot :  java -jar target/encheres-service-0.0.1-SNAPSHOT.jar
    l'initialisation la base de données sera faite dans cette étape via spring boot qui configure une base de données en mémoire de type H2.
    et des données de test (clients, liste des enchères actives) seront insérées dans la base de données au démarrage de l'application.
- lancer le frontend : application angular : npm start
- accéder à l'application : http://localhost:4200/encheres
- accéder à la documentation de l'api REST : http://localhost:8080/swagger-ui/index.html


# 6 . Stack technique utilisée :
- **Front-end** : <br>
*Angular CLI       : 21.2.7 <br>
  Angular           : 21.2.8 <br>
  Node.js           : 24.14.1 <br>
  Package Manager   : npm 11.11.0 <br>
  css, bootstrap*

- **Back-end** : <br>
*Java 25 <br>
Spring Boot 4.0.5 <br>
Hibernate <br>
API REST <br>
SWAGGER <br>
Base de donnée mémoire de type H2*