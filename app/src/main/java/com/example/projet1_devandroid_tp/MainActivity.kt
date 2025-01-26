
// zakariya 1.2
package com.example.projet1_devandroid_tp



import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projet1_devandroid_tp.ui.theme.Projet1_devAndroidTPTheme
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color





import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController


class MainActivity : ComponentActivity() {

     fun AdvancedLoginScreen(sharedPreferences: SharedPreferences?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            // Initialiser les SharedPreferences
            val sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE)

            // Vérifier si l'utilisateur est déjà connecté
            val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)


            // Définir le contenu de l'application
            setContent {
                val navController = rememberNavController() // Initialisation de NavHostController
                NavHost(navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(navController) // Passez le navController à l'écran Home
                    }
                    composable("task_list") {
                        TaskListScreen() // Écran de la liste des tâches

                Projet1_devAndroidTPTheme {
                    if (isLoggedIn) {
                        // Si l'utilisateur est connecté, on va directement à l'écran d'accueil
                        HomeScreen()
                    } else {
                        // Sinon, on affiche l'écran de login
                        AdvancedLoginScreen(sharedPreferences)


                        /* if (isLoggedIn) {
                         // Si connecté, on va directement à l'écran d'accueil
                         startActivity(Intent(this, HomeActivity::class.java))
                         finish() // Ferme l'activité actuelle pour éviter de revenir au login
                     } else {
                         // Sinon, on affiche l'écran de login


                     //enableEdgeToEdge()


                     setContent {
                         Projet1_devAndroidTPTheme {
                             Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                                 Greeting(
                                     name = "Android",
                                     modifier = Modifier.padding(innerPadding)


                                AdvancedLoginScreen(sharedPreferences) // Passe les SharedPreferences ici
                            )*/
                    }

                }
            }
        }

        //fun AdvancedLoginScreen(sharedPreferences: SharedPreferences?) {

        }
   // }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

   // @OptIn(ExperimentalMaterial3Api::class)

// Écran d'accueil après la connexion
@Composable
fun HomeScreen() {  // Code pour afficher l'écran d'accueil ici
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bienvenue dans le Gestionnaire de Tâches",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Bouton pour accéder à la liste des tâches
        Button(
            onClick = {navController.navigate("task_list")
            }, // Navigation vers la liste des tâches
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Voir les tâches")
        }
    }
}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AdvancedLoginScreen(sharedPreferences: SharedPreferences) {
        //val context = LocalContext.current
        var email by remember { mutableStateOf("") }
        var mp by remember { mutableStateOf("") }
        var email_error by remember { mutableStateOf("") }

        // Variable pour stocker l'état de l'erreur de validation
        var errorMessage by remember { mutableStateOf("") }



    // Code pour afficher l'écran de login ici
    fun validateEmail(input: String): Boolean {
        return input.contains("@") && input.contains(".") // Vérifie si '@' et '.' sont présents
    }

    fun validatePassword(input: String): Boolean {
        return input.length >= 6  // Simple validation (mot de passe >= 6 caractères)
    }



    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        // Logo de l'application
        Image(
            painter = painterResource(id = R.drawable.logo), // Remplace "logo" par le nom de ton image
            contentDescription = "Logo de l'application",
            modifier = Modifier
                .size(150.dp) // Taille de l'image
                .clip(RoundedCornerShape(16.dp)) // Optionnel: arrondir les coins de l'image
                .padding(8.dp), // Espacement autour de l'image
            contentScale = ContentScale.Crop // Optionnel: ajuster la mise à l'échelle de l'image
        )

        Spacer(modifier = Modifier.height(26.dp))

        // Champ de texte pour l'email amélioré avec icône, couleur et design personnalisé.
        OutlinedTextField(
            value = email,
            onValueChange = { email = it ;
                errorMessage = if (validateEmail(it)) "" else "L'email doit contenir '@' et '.'"
                            },
            label = { Text("Adresse e-mail") }, // Texte indicatif.
            placeholder = { Text("Entrer un email valide") }, // Exemple dans le champ.
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "e-mail") }, // Icône à gauche.
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue, // Couleur de la bordure lorsqu’il est sélectionné.
                unfocusedBorderColor = Color.Gray, // Couleur de la bordure lorsqu’il n’est pas sélectionné.
                cursorColor = Color.Blue // Couleur du curseur.
            ),
            //modifier = Modifier.fillMaxWidth(), // Le champ remplit la largeur disponible.
        )
        // Affichage des erreurs liées à l'email
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Champ de saisie pour le mot de passe
        OutlinedTextField(
            value = mp,
            onValueChange = { mp = it },
            label = { Text("Mot de pass") }, // Texte indicatif.
            placeholder = { Text("Entrer un mot de pass valide") }, // Exemple dans le champ.
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "e-mail") }, // Icône à gauche.
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue, // Couleur de la bordure lorsqu’il est sélectionné.
                unfocusedBorderColor = Color.Gray, // Couleur de la bordure lorsqu’il n’est pas sélectionné.
                cursorColor = Color.Blue // Couleur du curseur.
            ),
            //modifier = Modifier.fillMaxWidth(), // Le champ remplit la largeur disponible.
        )
        //if(!email_error.isEmpty()){
        Text(text = "Entrer un Mot de pass valide",
            modifier = Modifier
                .padding(2.dp),
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Affichage du message d'erreur en cas d'échec d'authentification















        // Bouton inchangé.
        Button(onClick = {
            // println("Email saisi : $email")

            // Validation des champs
            if (validateEmail(email) && validatePassword(mp)) {

            // Vérifier les informations d'identification de l'utilisateur
            if (email == "user@example.com" && mp == "password123") {
                // Sauvegarder l'état de la connexion dans SharedPreferences
                sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()

                // Naviguer vers l'écran d'accueil
                val context = LocalContext.current
                context.startActivity(Intent(context, HomeActivity::class.java))
                (context as Activity).finish() // Ferme l'activité de login après la connexion
            } else {
                // Afficher un message d'erreur si l'authentification échoue
                errorMessage = "Email ou mot de passe incorrect"
            }

        } else {
            // Message d'erreur si les champs sont invalides
            errorMessage = "Veuillez entrer un email et un mot de passe valides."
        }





        }) {
            Text("Se connecter")
        }
    }
}

        data class Task(val id: Int, val title: String, val description: String, val isCompleted: Boolean)
