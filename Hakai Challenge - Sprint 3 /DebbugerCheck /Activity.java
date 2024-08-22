// Configura o Handler e o Runnable para verificar o debugger e a depuração USB a cada 30 segundos
        handler = new Handler(Looper.getMainLooper());
        checkDebuggerRunnable = new Runnable() {
            @Override
            public void run() {
                // Verifica se o debugger está conectado ou aguardando conexão
                boolean isDebuggerConnected = DebugUtils.isDebuggerAttached();
                if (isDebuggerConnected) {
                    Snackbar.make(binding.getRoot(), "Debugger está conectado ou aguardando conexão.", Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(binding.getRoot(), "Nenhum debugger conectado.", Snackbar.LENGTH_SHORT).show();
                }

