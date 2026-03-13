package product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import user.RegisteredClient;

public class Carrito {
	private List<StoreProduct> sp = new ArrayList<>();
	private List<Pack> packs = new ArrayList<>();
	private boolean expired;
	private LocalDate modificationDate;
	
	public Carrito(boolean expired, LocalDate modificationDate) {
		this.expired = expired;
		this.modificationDate = modificationDate;
	}
	
	public boolean getExpired() {
		return this.expired;
	}
	
	public LocalDate getModificationDate() {
		return this.modificationDate;
	}
	
	public void setExpired(boolean exp) {
		this.expired = exp;
	}
	
	public void setModificationDate(LocalDate ld) {
		this.modificationDate = ld;
	}
	
	public double calculatePrice() {
		double aux = 0;
		
		for(StoreProduct spp: this.sp) {
			aux = spp.getPrice() + aux;
		}
		
		/**FALTA CONSEGUIR PRECIO DE PACKS*/
		
		return aux;
	}
	
	public void cancelProduct(StoreProduct toCancel) {
		int i = 0;
		
		if(this.sp.contains(toCancel) == true) {
			for(StoreProduct p: this.sp) {
				if(p == toCancel) {
					break;
				}
				i++;
			}
			
		this.sp.remove(i);
		toCancel.changeStock(toCancel.getStock()+1);
		}
	}
	
	public void cancelPack(Pack p) {
		int i = 0;
		
		if(this.packs.contains(p) == true) {
			for(Pack pack: this.packs) {
				if(p == pack) {
					break;
				}
				i++;
			}
		
		this.packs.remove(i);
		///cambiar stock de packs
		}
	}
	
	public void addProduct(StoreProduct wanted) {
		
		if(wanted.getStock() == 0) return;
		
		this.sp.add(wanted);
		wanted.changeStock(wanted.getStock()-1);
		wanted.setAddedDate(LocalDate.now());
	}
	
	public void addPack(Pack wanted) {
		this.packs.add(wanted);
		/**habría que cambiar stock, packs está en proceso**/
	}
	
	public int getProductAmount() {
		return this.sp.size();
	}
	
	public boolean payOrder() {
		double price = this.calculatePrice();
		
		Scanner sc = new Scanner(System.in);
		String numeroTarjeta, CCV, fechaCad;
		RegisteredClient rc;
		
		try {
			System.out.print("Introduce tu número de tarjerta: ");
			numeroTarjeta = sc.next();
			System.out.print("Introduce tu CCV: ");
			CCV = sc.next();
			System.out.print("Introduce tu fecha de caducidad de tarjeta: ");
			fechaCad = sc.next();
			
			/**para después de el lunes, comprobar si el formato es correcto, y si no, retorno false**/
		}catch (InputMismatchException e) {
            System.out.println("Error: El tipo de dato introducido no es válido.");
        } finally {
            sc.close();
        }
		
		this.packs.clear();
		this.sp.clear();
		
		return true;
	}
}


