
// zakariya 1.0
package com.example.projet1_devandroid_tp



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet1_devandroid_tp.ui.theme.Projet1_devAndroidTPTheme
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color





import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Projet1_devAndroidTPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    AdvancedLoginScreen()

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedLoginScreen() {

    fun validateEmail(input: String): Boolean {
        return input.contains("@") && input.contains(".") // Vérifie si '@' et '.' sont présents
    }


    var email by remember { mutableStateOf("") }
    var mp by remember { mutableStateOf("") }
    var email_error by remember { mutableStateOf("") }

    // Variable pour stocker l'état de l'erreur de validation
    var errorMessage by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

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

        // Champ de texte amélioré avec icône, couleur et design personnalisé.
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

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }



        Spacer(modifier = Modifier.height(16.dp))

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

        // Bouton inchangé.
        Button(onClick = {
            println("Email saisi : $email")
        }) {
            Text("Se connecter")
        }
    }
}


@Composable
fun HomeScreen(navController: NavController) {
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
            onClick = { navController.navigate("task_list") }, // Navigation vers la liste des tâches
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Voir les tâches")
        }
    }
}









data class Task(val id: Int, val title: String, val description: String, val isCompleted: Boolean)
