import ListaDuplamenteLigada.ListaDuplamenteLigadaDesordenada;
import ListaSimples.ListaSimplesDesordenada;

public class Main {
    public static void main (String[] args) {
        try {
            ListaDuplamenteLigadaDesordenada<Integer> lista = new ListaDuplamenteLigadaDesordenada<Integer>();
            for(int i = 0; i < 30; i++)
            {
                lista.insiraNoFim(i);
            }

            for (int i = 0; i < 20; i ++)
                lista.removaDoInicio();

            lista.remova(22);
            lista.remova(27);
            lista.removaDoFim();
            System.out.println(lista);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
