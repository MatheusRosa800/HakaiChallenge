// Verifica se a depuração USB está habilitada
                boolean isUsbDebuggingEnabled = DebugUtils.isUsbDebuggingEnabled(MainActivity.this);
                if (isUsbDebuggingEnabled) {
                    Snackbar.make(binding.getRoot(), "Depuração USB está habilitada.", Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(binding.getRoot(), "Depuração USB não está habilitada.", Snackbar.LENGTH_SHORT).show();
                }

                // Reposta o Runnable a cada 30 segundos
                handler.postDelayed(this, 30000); // 30000 milissegundos = 30 segundos
            }
        };
