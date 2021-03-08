package net.aristoft;

import net.aristoft.model.Categoria;
import net.aristoft.model.Perfil;
import net.aristoft.model.Usuario;
import net.aristoft.model.Vacante;
import net.aristoft.repository.CategoriasRepository;
import net.aristoft.repository.PerfilesRepository;
import net.aristoft.repository.UsuariosRepository;
import net.aristoft.repository.VacanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.*;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository repositoryCategorias;

	@Autowired
	private VacanteRepository repositoryVacante;

	@Autowired
	private UsuariosRepository repositoryUsuario;

	@Autowired
	private PerfilesRepository repositoryPerfiles;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(" >>>> INICIANDO DESDE LA CONSOLA");
		System.out.println(" >>>> VARIABLE REPO: " + repositoryCategorias);

		/********** Categoria - Metodos de la interface CrudRepository **********/
		/*this.guardar();
		this.buscarPorId();
		this.modificar();
		this.eliminar();
		this.conteo();
		this.eliminarTodos();
		this.encontarPorIds();
		this.buscartTodos();
		this.existeId();
		this.guardarTodo();*/

		/********** Categoria - Metodos de la interface JpaRepostory **********/
		/*this.buscarTodosJpa();
		this.deleteAllInBatch();
		this.buscarTodosOrdenados();
		this.sbuscarTodosOrdenadosPorNombre();
		this.buscarTodosPaginacion();
		this.buscarTodosPaginacionOrdenados();*/

		/********** Vacantes - Metodos de la interface JpaRepostory **********/
		/*this.buscarVacantes();
		this.guardarVacante();*/

		/********** Usuarios y Perfiles - Metodos de la interface JpaRepostory **********/
		/*this.crearPerfilesAplicacion();
		this.crearUsuarioListaPerfil();
		this.buscarUsuario();*/

		/****** Usuarios y Perfiles - SQL Methods **********/

		/*this.buscarVacantesPorEstatus();
		this.findByDestacadoAndEstatusOrderByIdDesc();
		this.buscarVacantesSalario();*/
		this.buscarVacantesVariosEstatus();
	}


	/*************************************** Métodos de la interface CrudRepository ********************************/
	private void guardar(){
		System.out.println(" >>>> Insertando un registro");
		Categoria categoria = new Categoria();
		categoria.setNombre("Finanzas");
		categoria.setDescripcion("Trabajos relacionados con finanzas y contabilidad");
		repositoryCategorias.save(categoria);

	}

	private void eliminar(){
		System.out.println(" >>>> eliminando un registro");
		int idCategoria = 1;
		repositoryCategorias.deleteById(idCategoria);
	}

	/*encuentra una entidad basado en un id*/
	private  void buscarPorId(){
		Optional<Categoria> optional = repositoryCategorias.findById(5);
		if(optional.isPresent()){
			System.out.println(" >>> Objeto categoria" + optional.get());
		}else{
			System.out.println(" >>> Categoria no encontrada");
		}
	}

	/**/

	private void modificar(){

		Optional<Categoria> optional = repositoryCategorias.findById(3);
		if(optional.isPresent()){
			System.out.println(" >>> Update de campos, Existe? " + optional.isPresent());

			Categoria catTmp = optional.get();
			System.out.println(catTmp);

			catTmp.setNombre("Ingenieria Matematicas");
			catTmp.setDescripcion("Desarrollo de programas de calidad");
			repositoryCategorias.save(catTmp);

			System.out.println(" >>> Objeto categoria" + optional.get());
		}else{
			System.out.println(" >>> Categoria no encontrada");
		}
	}

	public void conteo(){
		long  count = repositoryCategorias.count();
		System.out.println(">>> Total de categorias: " + count);
	}

	/*Este metodo no es muy eficiente ya que
	elimina los registros uno a uno con la sentencia
	delete*/
	private void eliminarTodos(){
		repositoryCategorias.deleteAll();
	}

	private void encontarPorIds(){
		List<Integer> ids =  new LinkedList<Integer>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		Iterable<Categoria> categorias = repositoryCategorias.findAllById(ids);

		for(Categoria cat : categorias){
			System.out.println(cat);
		}
	}

	/*Obtienen todos los registros de nuestra tabla*/
	private void buscartTodos(){
		Iterable<Categoria> categorias = repositoryCategorias.findAll();
		for (Categoria cat : categorias){
			System.out.println(cat);

		}
	}


	/*Valida si exste algun registro*/
	private void existeId(){
		boolean existe = repositoryCategorias.existsById(8);
		System.out.println(" >>>> La categoria existe? " + existe);
	}

	/*Sirve para guardar una lista de registros en 1 sola operacion*/
	private void guardarTodo(){
		List<Categoria> lista = this.getListaCategorias();
		repositoryCategorias.saveAll(lista);
	}

	private List<Categoria> getListaCategorias(){
		List<Categoria> lista = new LinkedList<>();

		Categoria cat = new Categoria();
		cat.setNombre("Programador de Blockchain");
		cat.setDescripcion("Trabajos relacionados con Bitcoin y Criptomonedas");
		lista.add(cat);

		cat = new Categoria();
		cat.setNombre("Soladador/Pintura");
		cat.setDescripcion("Trabajos relacionados con Soladador/Pintura");
		lista.add(cat);

		cat = new Categoria();
		cat.setNombre("Ingeniero Industrial");
		cat.setDescripcion("Trabajos relacionados con Ingeniero Industrial");
		lista.add(cat);

		return lista;
	}

	/*************************************** Métodos de la interface JpaRepository ********************************/

	/*
	Este método pertenece a JpaRepository
	Una de las diferencias es que este método ya regresa una Lista
	ya no una una colección uterable
	* */
	private  void buscarTodosJpa(){

		List<Categoria> catList = repositoryCategorias.findAll();

		for(Categoria cat : catList){
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
	}

	/*Borrar todos en lote  (Boraa todos los registros de la tabla)*/
	private void deleteAllInBatch(){
		repositoryCategorias.deleteAllInBatch();
	}

	/*
	Nos permite encontrar los registros ordenados por un campo y por paginación - PaginAndSortingRepository
		Se ordenan por default por la llave primaria
	* */
	private void buscarTodosOrdenados(){
		List<Categoria> catList = repositoryCategorias.findAll();

		for(Categoria cat : catList){
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
	}

	/*
	Nos permite encontrar los registros ordenados por un campo y por paginación - PaginAndSortingRepository
    Se ordenan por la propiedad nombre
	*/
	private void sbuscarTodosOrdenadosPorNombre(){
		List<Categoria> catList = repositoryCategorias.findAll(Sort.by("nombre").descending());

		for(Categoria cat : catList){
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
	}

	/*
	Busqueda por paginación  - Este método esta declarado en PaginAndSortinRepository
	*/
	public void buscarTodosPaginacion(){
		Page<Categoria> catCollectionPage  = repositoryCategorias.findAll(PageRequest.of(2, 3));

		System.out.println(">>>> Total de elementos: " + catCollectionPage.getTotalElements());
		System.out.println(">>>> Total de paginas: " + catCollectionPage.getTotalPages());
		System.out.println(">>>> Total de numero de elementos por pagina: " + catCollectionPage.getNumberOfElements());

		for(Categoria catElement : catCollectionPage.getContent()){
			System.out.println(catElement.getId() + " " + catElement.getNombre());
		}
	}

	/*Recuperación de las entidades ordenadas por un atributo*/
	private void buscarTodosPaginacionOrdenados(){

		Page<Categoria> catCollectionPage  = repositoryCategorias.findAll(PageRequest.of(2, 5, Sort.by("nombre").ascending()));

		System.out.println(">>>> Total de elementos: " + catCollectionPage.getTotalElements());
		System.out.println(">>>> Total de paginas: " + catCollectionPage.getTotalPages());
		System.out.println(">>>> Total de numero de elementos por pagina: " + catCollectionPage.getNumberOfElements());

		for(Categoria catElement : catCollectionPage.getContent()){
			System.out.println(catElement.getId() + " " + catElement.getNombre());
		}

	}

	/************* Métodos Vacantes **********/

	private void buscarVacantes(){
		List<Vacante> lista = repositoryVacante.findAll();
		for(Vacante vacante : lista){
			System.out.println(vacante.getId() + " " + vacante.getNombre() + " Categoria: " + vacante.getCategoria().getNombre());
		}
	}
	private void guardarVacante(){

		Vacante vacante = new Vacante();
		vacante.setNombre("Profesor de matematicas");
		vacante.setDescripcion("Profesor de Matematicas");
		vacante.setFecha(new Date());
		vacante.setSalario(8500.0);
		vacante.setEstatus("Aprobada");
		vacante.setDestacado(0);
		vacante.setImagen("escuela.png");

		Categoria cat = new Categoria();
		cat.setId(15);
		vacante.setCategoria(cat);

		vacante.setDetalles ("<h1>Los requisitos para profesor de matematicas</h1>");

		repositoryVacante.save(vacante);

	}


	/*Administracion de perfiles*/

	private List<Perfil> getPerfilesAplicacion(){
		List<Perfil> listPerfiles = new LinkedList<>();

		Perfil perfil = new Perfil();
		perfil.setPerfil ("SUPERVISOR");
		listPerfiles.add(perfil);

		perfil = new Perfil();
		perfil.setPerfil("ADMINISTRADOR");
		listPerfiles.add(perfil);

		perfil = new Perfil();
		perfil.setPerfil("USUARIO");
		listPerfiles.add(perfil);

		return listPerfiles;
	}

	private void crearPerfilesAplicacion(){
		List lista = this.getPerfilesAplicacion();
		repositoryPerfiles.saveAll(lista);
	}

	private void crearUsuarioListaPerfil(){
		Usuario user = new Usuario();
		user.setNombre("Guillermo Cadena");
		user.setEmail("guillermo.cdn.btz@gmail.com");
		user.setFechaRegistro(new Date());
		user.setUsername("gcadena");
		user.setPassword("1234");
		user.setEstatus(1);

		Perfil per1 = new Perfil();
		per1.setId(2);

		Perfil per2 = new Perfil();
		per2.setId(3);

		user.agregar(per1);
		user.agregar(per2);

		repositoryUsuario.save(user);
	}

	/*Buscar un usuario y desplegar sus perfiles asociados*/
	public void buscarUsuario(){
		Optional<Usuario> optional = repositoryUsuario.findById(1);

		if(optional.isPresent()){
			Usuario u = optional.get();

			System.out.println("Usuario: " + u.getNombre());
			System.out.println("Perfiles asignados: ");

			for( Perfil p : u.getPerfil()){
				System.out.println(p.getPerfil());
			}
		}else{
			System.out.println("Usuario no encontrado");
		}
	}

	/************ Consultas SQL ************/

	private void buscarVacantesPorEstatus(){
		List<Vacante> list = repositoryVacante.findByEstatus("Aprobada");
		System.out.println("Registros encontrados: " + list.size());
		for(Vacante v : list){
			System.out.println(" Vacante: " + v.getId() + " : " + v.getNombre() + " : " + v.getEstatus());
		}

	}

	public void findByDestacadoAndEstatusOrderByIdDesc(){

		List<Vacante> list = repositoryVacante.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
		System.out.println("Registros encontrados: " + list.size());
		for(Vacante v : list){
			System.out.println(v.getId() + " : " + v.getNombre() + " : "  + v.getEstatus() + " : " + v.getDestacado());
		}
	}

	public void buscarVacantesSalario() {

		List<Vacante> list = repositoryVacante.findBySalarioBetweenOrderBySalarioAsc(7000 ,  14000);
		System.out.println("Registros encontrados: " + list.size());
		for(Vacante v : list){
			System.out.println(v.getId() + " : " + v.getNombre() + " : "  + v.getSalario() );
		}
	}

	public void buscarVacantesVariosEstatus(){
		String[] estatus = new String[]{"Eliminada", "Creada", "Aprobada"};
		List<Vacante> list = repositoryVacante.findByEstatusIn(estatus);

		System.out.println("Registros encontrados: " + list.size());
		for(Vacante v : list){
			System.out.println(v.getId() + " : " + v.getNombre() + " : "  + v.getEstatus() );
		}
	}


}