// Inicia o primeiro ciclo de verificação com um atraso de 1 segundo após a verificação de ROOT
        handler.postDelayed(checkDebuggerRunnable, 1000); // 1000 milissegundos = 1 segundo

        // Configura a navegação
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Inicializa os elementos de interface do usuário
        editTextInput = findViewById(R.id.edit_text_input);
        buttonSave = findViewById(R.id.button_save);
        textViewDisplay = findViewById(R.id.text_view_display);

        // Inicializa a classe SharedPreferencesManager
        sharedPreferencesManager = SharedPreferencesManager.getInstance(this);

        // Restaura o texto salvo, se houver
        String savedText = sharedPreferencesManager.getText();
        textViewDisplay.setText("Texto salvo: " + savedText);

        // Adiciona um ouvinte de clique ao botão de salvar
        buttonSave.setOnClickListener(v -> {
            // Obtém o texto do EditText
            String inputText = editTextInput.getText().toString();

            // Verifica se o texto não está vazio
            if (!inputText.isEmpty()) {
                // Salva o texto nas SharedPreferences
                sharedPreferencesManager.saveText(inputText);

                // Atualiza o TextView para exibir o texto salvo
                textViewDisplay.setText("Texto salvo: " + inputText);

                // Limpa o EditText após salvar
                editTextInput.setText("");

                // Mostra uma mensagem de confirmação
                Toast.makeText(MainActivity.this, "Texto salvo com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                // Se o EditText estiver vazio, exibe uma mensagem de aviso
                Toast.makeText(MainActivity.this, "Por favor, insira algum texto", Toast.LENGTH_SHORT).show();
            }
        });
    }
