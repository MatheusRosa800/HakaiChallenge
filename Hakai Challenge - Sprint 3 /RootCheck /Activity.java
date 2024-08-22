Main Activity

// Verifica se o dispositivo está com acesso ROOT e exibe uma mensagem
        RootCheck rootCheck = new RootCheck();
        boolean isRooted = rootCheck.isDeviceRooted(this);
        if (isRooted) {
            Snackbar.make(binding.getRoot(), "O dispositivo está com acesso ROOT.", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(binding.getRoot(), "O dispositivo não está com acesso ROOT.", Snackbar.LENGTH_SHORT).show();
        }
