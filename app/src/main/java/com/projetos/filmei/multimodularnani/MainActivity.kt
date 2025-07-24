package com.projetos.filmei.multimodularnani

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.projetos.filmei.datastore.settings.AppSettings
import com.projetos.filmei.datastore.settings.AppSettingsSerializer
import com.projetos.filmei.datastore.settings.Language
import com.projetos.filmei.datastore.settings.Location
import com.projetos.filmei.multimodularnani.ui.theme.MultiModularNaniTheme
import com.projetos.filmei.protodatastore.manager.preferences.PreferencesDataStoreInterface
import com.projetos.filmei.protodatastore.manager.session.SessionDataStoreInterface
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sessionDataStoreInterface: SessionDataStoreInterface

    @Inject
    lateinit var preferencesDataStoreInterface: PreferencesDataStoreInterface

    private lateinit var appSettingDataStore: DataStore<AppSettings>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appSettingDataStore = DataStoreFactory.create(
            serializer = AppSettingsSerializer(),
            produceFile = { dataStoreFile("app_settings.json") },
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        )

        // val room = Room.databaseBuilder()
        enableEdgeToEdge()
        setContent {
            MultiModularNaniTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SettingsScreen(
                        sessionDataStoreInterface,
                        appSettingDataStore,
                        Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsScreen(
    sessionDataStoreInterface: SessionDataStoreInterface,
    appSettingDataStore: DataStore<AppSettings>,
    modifier: Modifier,
) {
    val scope = rememberCoroutineScope()
    val appSettings by appSettingDataStore.data.collectAsState(initial = AppSettings())

    val accessTokenFlow by sessionDataStoreInterface.getAccessTokenFlow()
        .collectAsState(initial = "")

    var accessTokenValue by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(50.dp)) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "AccessTokenFlow: $accessTokenFlow")

        Spacer(modifier = Modifier.height(16.dp))

        LaunchedEffect(Unit) {
            scope.launch {
                accessTokenValue = sessionDataStoreInterface.getAccessToken()
            }
        }

        Text(text = "AccessToken: $accessTokenValue")

        Text(text = "Language: " + appSettings.language)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            scope.launch {
                sessionDataStoreInterface.setAccessToken("Access Token " + System.currentTimeMillis())
            }
        }) {
            Text(text = "Inserir")
        }

        Text(text = "Last known locations: ")
        appSettings.lastKnownLocations.forEach { location ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Lat: ${location.lat} Lng: ${location.lng}")
        }
        Spacer(modifier = Modifier.height(16.dp))
        val newLocation = Location(37.123, 122.908)

        Language.entries.forEach { language ->
            DropdownMenuItem(text = { Text(text = language.name) }, onClick = {
                scope.launch {
                    appSettingDataStore.updateData { currentSettings ->
                        currentSettings.copy(
                            language = language,
                            lastKnownLocations = currentSettings.lastKnownLocations.add(
                                newLocation,
                            ),
                        )
                    }
                }
            })
        }
    }
}
