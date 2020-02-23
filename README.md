# TabAleatoire
Générateur de tablature aléatoire, entièrement conçu par mes soins.

V 0.1.0
Vous pouvez générer une tablature aléatoire de 1 à 6 cordes.
Ces tablatures respecteront la gamme choisie (voir les gammes et leur numéro attribué plus bas).
Listes des paramètres que prennent (pour cette version) en compte les tablatures:
	- tonalite: définit la tonalité de la gamme, par défaut DO (0)
	- nb_corde: définit le nombre de cordes générée par la tablature
	- nb_temps: définit le nombre de temps, et donc la longueur de la tablature (dans cette version : 1 temps = 1 note ou 1 silence)
	- repartition: modifie la répartition des notes, une valeur faible (proche de 0) favorise les cordes aigues, contrairement à une valeur élevée (proche de 150).
	               la répartition est sur 50 par défaut, valeur arbitraire qui permet une répartition (assez) homogène
	- case_min et case_max (alpha): définit les bornes minimale et maximale à ne pas dépasser
					par défaut case_min=0, case_max=12;
	- num_gamme: définit la gamme utilisée par le numéro correspondant (voir correspondance plus bas)
	- accordage (non testé) : permet de jouer à la bonne tonalité même avec un accordage non-standard
				  par défaut en LA (0)
	- estMelodie: définit si la tablature est une mélodie,  les notes ne seront donc pas superposées (dans cette version) si estMelodie est true.
	              estMelodie est true par défaut.
	

La plupart des gammes dites "standardes" sont disponibles.
Liste exhaustive des gammes disponibles dans cette version:
	0 - Chromatique
	1 -(Naturelle) Majeure
	2 - Naturelle Mineure
	3 - Harmonique Mineure
	4 - Mélodique Mineure
	5 - Mixolydianne
	6 - Phrygianne
	7 - Pentatonique Majeure
	8 - Pentatonique Mineure
	9 - Blues Majeure
       10 - Blues Mineure      

Fonctionnalités principales :
// à remplir
newRandom()
Permet de générer une tablature aléatoire selon les paramètres préalablement choisis.



Fonctionnalités supplémentaires :
Une tablature aléatoire renvoie un id_tab qui permet de recréer la tablature 
(garantie si la tablature a été créée avec la même version du programme, car certaines versions peuvent 
apporter des changement à l'algorithme aléatoire, rendant l'ID obsolète)
Il est néanmoins conseillé en l'état de sauvegarder la tablature dans un fichier .txt par copier/coller.

Création d'un objet NoteFlow (béta), qui renvoie les notes à la place des numéros des cases, 
utile pour avoir une trace sous une autre forme. Ne garde pas encore les octaves.

Génération d'une gamme (alpha), qui permet de générer (presque) toutes les notes d'une gamme dans l'ordre.
Permet d'indentifier la gamme, ou de s'entrainer à la jouer. La limite des cases n'est pas bien définie, elle ne permet
donc pas correctement la jouer à la guitare (avec le doigté standard) et peut manquer des notes qui sont hors des bornes. 
