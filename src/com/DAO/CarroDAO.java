package com.DAO;


import java.util.List;


import com.Entity.Carro;


public class CarroDAO {

	  public Carro inserirCarro(Carro carro) {
	        Carro carroInserir  = new Carro();
	        carroInserir.setMarca(carro.getMarca());
	        carroInserir.setModelo(carro.getModelo());
	        carroInserir.setAno(carro.getAno());
	        return carroInserir;
	    }


	    public Carro findByModelo(String modelo, List<Carro> listaCarro) {
	        for (int i = 0; i < listaCarro.size(); i++) {
	            if(listaCarro.get(i).getModelo().equals(modelo)) {
	                return listaCarro.get(i);
	            }
	        }
	        return null;
	    }
	    public void Alterar(Carro produto, String modelo, List<Carro> listaCarro) {
	        for (int i = 0; i < listaCarro.size(); i++) {
	            if(listaCarro.get(i).getModelo().equals(modelo)) {
	                listaCarro.set(i, produto);
	            }
	        }
	    }
	    public void Deletar(String modelo, List<Carro> listaCarro) {
	        for (int i = 0; i < listaCarro.size(); i++) {
	            if(listaCarro.get(i).getModelo().equals(modelo)) {
	                listaCarro.remove(i);
	            }
	        }
	    }
	

}
