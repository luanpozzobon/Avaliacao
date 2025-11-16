import plant.NuclearPlant;

public class Main {
    public static void main(String[] args) {
        NuclearPlant usina = new NuclearPlant();

        usina.powerOn();
        usina.setTemperature(50);
        usina.checkConditions();
        System.out.println("Estado atual: " + usina.getState());

        System.out.println("\n=== TESTE 1: Aumento de Temperatura (Normal -> Amarelo) ===");
        usina.setTemperature(301);
        usina.checkConditions();

        System.out.println("\n=== TESTE 2: Aumento Crítico (Amarelo -> Vermelho) ===");
        usina.setTemperature(401);
        usina.checkConditions();
        sleep(10);
        usina.checkConditions();

        sleep(30);
        usina.checkConditions();

        System.out.println("\n=== TESTE 3: Falha Crítica (Vermelho -> Emergência) ===");
        System.out.println("Sistema de resfriamento falha!");
        usina.setCoolingFailed(true);
        usina.checkConditions();

        System.out.println("\n=== TESTE 4: Tentativa de Manutenção em Emergência ===");
        usina.setTemperature(50);
        usina.setCoolingFailed(false);
        usina.checkConditions();
        System.out.println("Estado atual: " + usina.getState());

        // Tentar entrar em manutenção (deve falhar)
        usina.enterMaintenance();
        System.out.println("Estado atual: " + usina.getState());


        System.out.println("\n\n#############################################");
        System.out.println("### CENÁRIO 2: Teste do Modo Manutenção ###");
        System.out.println("#############################################");

        NuclearPlant usina2 = new NuclearPlant();
        usina2.powerOn();
        usina2.setTemperature(50);
        usina2.checkConditions();

        usina2.enterMaintenance();
        System.out.println("Estado atual: " + usina2.getState());

        System.out.println("\nAumentando temperatura durante manutenção...");
        usina2.setTemperature(350);
        usina2.checkConditions();
        System.out.println("Estado atual: " + usina2.getState());

        usina2.leaveMaintenance();
        System.out.println("Estado atual: " + usina2.getState());
    }

    private static void sleep(int segundos) {
        try {
            long tempoSimuladoMs = segundos * 1000L;
            Thread.sleep(tempoSimuladoMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}