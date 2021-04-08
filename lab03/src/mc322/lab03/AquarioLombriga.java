public class AquarioLombriga {
    int size_aqua;
    int size_lomb;
    int pos_head;
    String lado_lomb;
    
    AquarioLombriga(int size_aqua, int size_lomb, int pos_tail) {
        this.size_aqua = size_aqua;
        this.size_lomb = size_lomb;
        this.pos_head = pos_tail + (size_lomb - 1);
        this.lado_lomb = "dir";
    }
    
    public void crescer() {
        int pos_cauda;
        if (this.lado_lomb == "dir") {
            pos_cauda = this.pos_head - (this.size_lomb - 1);
            if (pos_cauda > 1)
                this.size_lomb++;
        } else {
            pos_cauda = this.pos_head + (this.size_lomb - 1);
            if (pos_cauda < this.size_aqua)
                this.size_lomb++;
        }
    }
    
    public void mover() {
        if (this.lado_lomb == "dir") {
            if (this.pos_head < this.size_aqua)
                this.pos_head++;
            else
                this.virar();
        } else {
            if (this.pos_head > 1)
                this.pos_head--;
            else
                this.virar();
        }
    }
    
    public void virar() {
        if (this.lado_lomb == "dir") {
            this.lado_lomb = "esq";
            this.pos_head -= (this.size_lomb - 1);
        } else {
            this.lado_lomb = "dir";
            this.pos_head += (this.size_lomb - 1);
        }
    }
    
    public void apresenta() {
        int start_lomb, finish_lomb;
        if (this.lado_lomb == "dir") {
            finish_lomb = this.pos_head;
            start_lomb = this.pos_head - (this.size_lomb - 1);
        } else {
            start_lomb = this.pos_head;
            finish_lomb = this.pos_head + (this.size_lomb - 1);
        }

        for (int i = 1; i <= this.size_aqua; i++) {
            if (i < start_lomb || i > finish_lomb)
                System.out.print("#");
            else {
                if (i == this.pos_head)
                    System.out.print("O");
                else
                    System.out.print("@");
            }
        }
        System.out.print("\n");
    }
}