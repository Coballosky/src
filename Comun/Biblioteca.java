package Comun;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Observable;

import javafx.scene.control.TextArea;

public class Biblioteca extends Observable{
	private ArrayList<Libro> libros;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Cubiculo> cubiculos;
	
	//Constructores
	public Biblioteca() {
		libros = new ArrayList<Libro>();
		cubiculos = new ArrayList<Cubiculo>();
		usuarios = new ArrayList<Usuario>();
	}
	public Biblioteca(ArrayList<Libro> libros, ArrayList<Cubiculo> cubiculos) {
		this.libros = libros;
		this.cubiculos = cubiculos;
		this.usuarios = new ArrayList<Usuario>();
	}
	public Biblioteca(ArrayList<Libro> libros, ArrayList<Cubiculo> cubiculos,ArrayList<Usuario> usuarios) {
		this.libros = libros;
		this.cubiculos = cubiculos;
		this.usuarios = usuarios;
	}

	//Getter & Setter
	public void setLibros(ArrayList<Libro> listaLibros) {
		this.libros = listaLibros;
	}
	public ArrayList<Libro> getListaLibros(){
		return libros;
	}
	public void setUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.usuarios = listaUsuarios;
	}
	public ArrayList<Usuario> getUsuarios(){
		return usuarios;
	}
	public void setCubiculos(ArrayList<Cubiculo> cubiculos) {
		this.cubiculos = cubiculos;
	}
	public ArrayList<Cubiculo> getCubiculos(){
		return cubiculos;
	}
	
	
	//METODOS LIBROS
	public Libro buscarLibro(String nombreLibro) {	//Busca libro, cualquier copia
		if(libros != null && !libros.isEmpty()) {
			ListIterator<Libro> iterador = libros.listIterator();
			while(iterador.hasNext()) {
				Libro libroItr = (Libro)iterador.next();
				if(libroItr.getTitulo().equalsIgnoreCase(nombreLibro)) {
					return libroItr;
				}
			}
		}
		
		return null;
	}
	
	public boolean buscarLibro(int codNum) {		//Para generacion de codigo unico
		if(libros != null && !libros.isEmpty()) {
			ListIterator<Libro> iterador = libros.listIterator();
			while(iterador.hasNext()) {
				String[] codAlfaNumerico = (String[])iterador.next().getCode().split("-");
				int num = Integer.parseInt(codAlfaNumerico[1]);
				if(num == codNum) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public Libro buscarLibro(String codTipo, int codNum) {
		if(libros != null && !libros.isEmpty()) {
			String codigo = codTipo + "-" + Integer.toString(codNum);
			ListIterator<Libro> iterador = libros.listIterator();
			while(iterador.hasNext()) {
				Libro libroItr = (Libro)iterador.next();
				if(libroItr.getCode().equals(codigo)) {
					return libroItr;
				}
			}
		}
		return null;
	}
	
	public void modificarLibro(String codTipo, int codNum, String campo, Object dato) {
		if(buscarLibro(codTipo,codNum) != null) {
			buscarLibro(codTipo,codNum).modificarLibro(campo, dato);
		}
	}
	
	public boolean agregarLibro(Libro nuevoLibro) {
		String[] codigo = nuevoLibro.getCode().split("-");
		if(buscarLibro(codigo[0],Integer.parseInt(codigo[1])) == null) {
			libros.add(nuevoLibro);
			return true;
		}
		return false;
	}
	
	public boolean eliminarLibro(String codTipo, int codNum) {	//Elimina libro especifico
		String codigo = codTipo + Integer.toString(codNum);
		Libro buscado = buscarLibro(codTipo,codNum);
			if(buscado != null && buscado.getCode().equals(codigo)) {
				libros.remove(buscado);
				return true;
			}
		return false;
	}
	
	public ArrayList<String> listaLibros(){
		if(libros != null && !libros.isEmpty()) {
			ArrayList<String> listLibros = new ArrayList<String>(libros.size());
			ListIterator<Libro> itrLibros = libros.listIterator();
			while(itrLibros.hasNext()) {
				Libro actual = (Libro) itrLibros.next();
				listLibros.add(actual.infoLibro());
				itrLibros.next();
			}
		}
		return null;
	}
	
	public void reporteLibros(String fileOut) throws IOException {
		ArrayList<String> lista = listaLibros();
		if(lista != null && !lista.isEmpty()) {
			ListIterator<String> iterador = lista.listIterator();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOut)));
			while(iterador.hasNext()) {
				bw.write(iterador.next());
				iterador.next();
			}
			bw.close();
		}
	}
	
	public void reporteLibros(TextArea texto) {
		ArrayList<String> lista = listaLibros();
		if(lista != null && lista.isEmpty()) {
			ListIterator<String> iterador = lista.listIterator();
			while(iterador.hasNext()) {
				texto.appendText(iterador.next());
				iterador.next();
			}
		}
	}
	
	//METODOS USUARIOS
	
	public boolean agregarUsuario(Usuario nuevoUsuario) {
		if(buscarUsuario(nuevoUsuario.getRut()) == null) {
			usuarios.add(nuevoUsuario);
			return true;
		}
		return false;
	}
	
	public Usuario buscarUsuario(String rutBuscado) {
		ListIterator<Usuario> iterador = usuarios.listIterator();
		while(iterador.hasNext()) {
			Usuario usuarioItr = (Usuario)iterador.next();
			if(usuarioItr.getRut().equals(rutBuscado)) {
				return usuarioItr;
			}
			iterador.next();
		}
		return null;
	}
	
	public void modificarUsuario(String rut, String campo, Object dato) {
		if(buscarUsuario(rut) != null) {
			buscarUsuario(rut).modificarUsuario(campo,dato);
		}
	}
	
	public boolean eliminarUsuario(String rutEliminar) {
		Usuario buscado = buscarUsuario(rutEliminar);
		if(buscado != null && buscado.getNombres().equals(rutEliminar)) {
			usuarios.remove(buscado);
			return true;
		}
		return false;
	}
	
	public ArrayList<String> listaUsuarios(){
		ArrayList<String> listUsuarios = new ArrayList<String>(usuarios.size());
		ListIterator<Usuario> itrUsers = usuarios.listIterator();
		while(itrUsers.hasNext()) {
			Usuario actual = (Usuario) itrUsers.next();
			listUsuarios.add(actual.infoUsuario());
		}
		return listUsuarios;
	}
	
	public void reporteUsuarios(String fileOut) throws IOException {
		ArrayList<String> usuarios = listaUsuarios();
		ListIterator<String> iterador = usuarios.listIterator();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOut)));
		while(iterador.hasNext()) {
			bw.write(iterador.next());
			iterador.next();
		}
		bw.close();
	}
	
	public void reporteUsuarios(TextArea texto) {
		ArrayList<String> lista = listaUsuarios();
		ListIterator<String> iterador = lista.listIterator();
		while(iterador.hasNext()) {
			texto.appendText(iterador.next());
			iterador.next();
		}
	}
	
}