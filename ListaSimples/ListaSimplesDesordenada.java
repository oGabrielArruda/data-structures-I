package ListaSimples;

import java.lang.reflect.Method;

public class ListaSimplesDesordenada<X> {
    protected class No
    {
        private X info;
        private No prox;

        public No(X info, No prox)
        {
            this.info = info;
            this.prox = prox;
        }

        public No(X info)
        {
            this.info = info;
        }

        public void setProx(No prox)
        {
            this.prox = prox;
        }
        public void setInfo(X info)
        {
            this.info = info;
        }

        public X getInfo()
        {
            return this.info;
        }
        public No getProx()
        {
            return this.prox;
        }
    }

    protected No primeiro, ultimo;
    public ListaSimplesDesordenada()
    {
        this.primeiro = this.ultimo = null;
    }

    public X meuCloneDeX(X x)
    {
        X ret = null;
        try
        {
            Class<?> classe = x.getClass();
            Class<?>[] tiposDosParms = null; // null pq clone nao tem parametros
            Method metodo = classe.getMethod("clone",tiposDosParms);
            Object[] parms = null; // null pq clone nao tem parametros
            ret = (X)metodo.invoke(x,parms);
        }
        catch (Exception erro)
        {} // pq sei que estou chamando clone de um objeto que é Cloneable e, portanto, nao há risco do método não existir ou de ser chamado com parametros errado

        return ret;
    }

    public void insiraNoInicio(X info) throws Exception
    {
        if(info == null)
            throw new Exception("Info nula");

        X inserir = null;
        if(info instanceof  Cloneable)
            inserir = this.meuCloneDeX(info);
        else
            inserir = info;

        this.primeiro = new No(inserir, this.primeiro);
        if(this.ultimo == null)
            this.ultimo = this.primeiro;
    }

    public void insiraNoFim(X info) throws Exception
    {
        if(info == null)
            throw new Exception("info nula");
        X inserir = null;
        if(info instanceof  Cloneable)
            inserir = this.meuCloneDeX(info);
        else
            inserir = info;

        if(this.ultimo == null)
        {
            this.ultimo = new No(inserir);
            this.primeiro = this.ultimo;
        }
        else
        {
            this.ultimo.setProx(new No(inserir));
            this.ultimo = this.ultimo.getProx();
        }
    }

    public void removaDoInicio() throws Exception
    {
        if(this.primeiro == null)
            throw new Exception("primeiro null");
        if(this.primeiro == this.ultimo)
        {
            this.primeiro = null;
            this.ultimo = null;
            return;
        }

        this.primeiro = this.primeiro.getProx();
    }

    public void removaDoFim() throws Exception
    {
        if(this.ultimo == null)
            throw new Exception("Ultimo nulo");
        if(this.ultimo == this.primeiro)
        {
            this.ultimo = null;
            this.primeiro = null;
            return;
        }
        No atual;
        for(atual = this.primeiro; atual.getProx() != this.ultimo; atual = atual.getProx());
        atual.setProx(null);
        this.ultimo = atual;
    }

    public void remova(X x) throws Exception
    {
        if(x == null)
            throw new Exception("elemento nulo");
        if (this.primeiro == null)
            throw new Exception("lista vazia");
        if(x.equals(this.primeiro.getInfo()))
        {
            if(this.primeiro == this.ultimo)
                this.ultimo = null;
            this.primeiro = this.primeiro.getProx();
            return;
        }

        No atual = this.primeiro;
        for(;;)
        {
            if(atual.getProx() == null)
                throw new Exception("Elemento nao existe");

            if(x.equals(atual.getProx()))
            {
                atual.setProx(atual.getProx().getProx());
                if(atual.getProx() == null)
                    this.ultimo = atual;
                return;
            }

            atual = atual.getProx();
        }
    }

    public boolean tem(X info) throws Exception
    {
        if(info == null)
            throw new Exception("Info inexistente");

        if(this.primeiro == null)
            return false;

        No atual;
        for(atual = this.primeiro; atual != null; atual = atual.getProx())
        {
            if(info.equals(atual.getInfo()))
                return true;
        }
        return false;
    }

    public int getQtd()
    {
        int qtd = 0;
        No atual = this.primeiro;
        while(atual != null)
        {
            qtd++;
            atual = atual.getProx();
        }
        return qtd;
    }

    public X getDoInicio() throws Exception
    {
        if(this.primeiro == null)
            throw new Exception("Lista vazia");
        X ret = this.primeiro.getInfo();
        if(ret instanceof Cloneable)
            ret = meuCloneDeX(ret);
        return ret;
    }

    public X getDoFim() throws Exception
    {
        if(this.primeiro == null)
            throw new Exception("lista vazia");
        X ret = this.ultimo.getInfo();
        if(ret instanceof Cloneable)
            ret = meuCloneDeX(ret);
        return ret;
    }
    public boolean isVazia()
    {
        return this.primeiro == null;
    }

    public void invertaSe ()
    {
        if (this.primeiro==null)
            return; // lista vazia; nao há o que inverter

        if (this.primeiro.getProx() == null)
            return; // lista com um elemento só; nao ha o que inverter

        // tendo 2 ou mais nós, percorre invertendo
        No anterior=null, atual=this.primeiro, seguinte=atual.getProx();
        while (seguinte!=null)
        {
            atual.setProx (anterior);
            anterior = atual;
            atual    = seguinte;
            seguinte = seguinte.getProx();
        }
        this.ultimo.setProx(anterior);

        // this.primeiro vira this.ultimo e vice-versa
        No   backup   = this.primeiro;
        this.primeiro = this.ultimo;
        this.ultimo   = backup;
    }

    public String toString()
    {
        String ret = "[ ";
        No atual = this.primeiro;
        while(atual != null) {
            ret += atual.getInfo() + " ";
            atual = atual.getProx();
        }
        ret += "]";
        return ret;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(this.getClass() != obj.getClass())
            return false;
        if(this == obj)
            return true;

        ListaSimplesDesordenada listaDeso = (ListaSimplesDesordenada) obj;

        No atualThis = this.primeiro;
        No atualObj = listaDeso.primeiro;
        while(atualThis != null && atualObj != null)
        {
            if(!atualThis.getInfo().equals(atualObj.getInfo()))
                return false;

            atualObj = atualObj.getProx();
            atualThis = atualThis.getProx();
        }

        if(atualThis != null)
            return false;
        if(atualObj != null)
            return false;
        return true;
    }

    public int hashCode()
    {
        int ret = 345;
        for(No atual = this.primeiro; atual != null; atual = atual.getProx())
        {
            ret = ret * 17 + atual.getInfo().hashCode();
        }

        if(ret<0)
            ret = -ret;
        return ret;
    }

    public ListaSimplesDesordenada(ListaSimplesDesordenada modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("modelo nulo");
        if(modelo.primeiro == null)
            return;

        this.primeiro = new No((X) modelo.primeiro.getInfo());

        No atualDoThis = this.primeiro;
        No atualDoModelo = modelo.primeiro.getProx();

        while(atualDoModelo != null)
        {
            atualDoThis.setProx(new No(atualDoModelo.getInfo()));
            atualDoModelo = atualDoModelo.getProx();
            atualDoThis = atualDoThis.getProx();
        }

        this.ultimo = atualDoThis;

    }

    public Object clone()
    {
        ListaSimplesDesordenada ret = null;
        try
        {
            ret = new ListaSimplesDesordenada(this);
        }
        catch (Exception ex)
        { }
        return ret;
    }
}
