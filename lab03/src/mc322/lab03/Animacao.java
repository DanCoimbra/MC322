public class Animacao {
    AquarioLombriga aqualomb;
    String actions;
    boolean primeira_exibicao;
    
    Animacao(String inputlist) {
        // Se inputlist for curto demais, é preenchido com zero
        if (inputlist.length() < 6)
            inputlist += "0".repeat(6 - inputlist.length());

        // Se inputlist não começar com 6 caracteres numéricos, tais caracteres são preenchidos com zero
        try {
            int numeric_inputlist = Integer.parseInt(inputlist.substring(0,6));
        } catch (NumberFormatException e) {
            inputlist = "000000" + inputlist.substring(6);
        }

        // Converte o input para números
        int size_aqua = Integer.parseInt(inputlist.substring(0,2));
        int size_lomb = Integer.parseInt(inputlist.substring(2,4));
        int pos_tail = Integer.parseInt(inputlist.substring(4,6));
        int pos_head = pos_tail + (size_lomb - 1);

        // Captura erros de dimensionamento
        if (size_lomb <= 0)
            size_lomb = 1;

        if (pos_tail <= 0)
            pos_tail = 1;

        if (pos_head > size_aqua)
            size_aqua = pos_head;

        // Inicializa as variáveis de estado
        this.aqualomb = new AquarioLombriga(size_aqua, size_lomb, pos_head);
        this.actions = inputlist.substring(6);
        this.primeira_exibicao = true;
    }
    
    public void apresenta() {
        this.aqualomb.apresenta();
    }
    
    public boolean passo() {
        if (primeira_exibicao) {
            this.apresenta();
            this.primeira_exibicao = false;
        }

        if (this.actions.length() > 0) {
            if (this.actions.charAt(0) == 'C')
                this.aqualomb.crescer();
            else if (this.actions.charAt(0) == 'M')
                this.aqualomb.mover();
            else
                this.aqualomb.virar();
            
            this.actions = this.actions.substring(1);
            return true;
        } else
            return false;
    }
}