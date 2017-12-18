package modelo;

import java.util.*;

public class Programa {

    private List<Premio> premios;
    private List<Participante> participantes;
    private Scanner scan;
    private String comando;
    private String[] params;
    private int idPremio;
    private int idParticipante;
    private int suma;
    private double promedio;
    private Random r;
    private boolean cRifo;

    public Programa() {
        premios = new ArrayList<>();
        participantes = new ArrayList<>();

        scan = new Scanner(System.in);
        idPremio = 4;
        idParticipante = 4;

        crearTODO();
        r = new Random();
        cRifo = false;
    }

    /**
     * Acá esto todo el programa
     */
    public void ejecutar() {
        System.out.println("Bienvenido al terminal tierno v0.1");
        while (true) {
            System.out.print("> ");
            comando = scan.nextLine();

            if (comando.equals("/salir")) {
                break;
            }

            params = comando.split(" ");

            switch (params[0]) {
                case "/crear":
                    try {
                        switch (params[1]) {
                            case "-pr":
                                String descripcion;
                                int precio;

                                String[] params2 = comando.split("\"");

                                descripcion = params2[1];
                                precio = Integer.parseInt(params2[2].trim());

                                Premio pre = new Premio(idPremio, descripcion, precio);
                                idPremio++;

                                premios.add(pre);
                                System.out.println("Premio creado!");
                                break;
                            case "-pa":
                                String nombre;
                                String telefono;

                                params2 = comando.split("\"");

                                nombre = params2[1];
                                telefono = params2[2];

                                Participante pa = new Participante(idParticipante, nombre, telefono);
                                idParticipante++;

                                participantes.add(pa);
                                System.out.println("Participante creado!");
                                break;
                            default:
                                help();
                        }
                    } catch (Exception e) {
                        help();
                    }

                    break;

                case "/listar":
                    try {
                        switch (params[1]) {
                            case "-pr":
                                for (Premio p : premios) {
                                    System.out.println(p);
                                }
                                break;
                            case "-pa":
                                for (Participante par : participantes) {
                                    System.out.println(par);
                                }
                                break;

                            case "-t":
                                for (Premio p : premios) {
                                    System.out.println(p);
                                }
                                for (Participante par : participantes) {
                                    System.out.println(par);
                                }
                                break;
                            default:
                                help();
                                break;
                        }
                    } catch (Exception e) {
                        help();
                    }
                    break;

                case "/buscar":
                    try {
                        switch (params[1]) {
                            case "-pr":
                                switch (params[2]) {
                                    case "-i":
                                        int idPremioBuscar = Integer.parseInt(params[3]);

                                        boolean cEncontro = false;

                                        for (Premio p : premios) {
                                            if (p.getId() == idPremioBuscar) {
                                                System.out.println(p);
                                                cEncontro = true;
                                                break;
                                            }
                                        }

                                        if (!cEncontro) {
                                            System.out.println("El premio [" + idPremioBuscar + "] no se encontró");
                                        }

                                        break;
                                    case "-d":

                                        cEncontro = false;

                                        String descPremio = params[3].toLowerCase().trim();

                                        for (Premio p : premios) {
                                            if (p.getDescripcion().toLowerCase().contains(descPremio)) {
                                                System.out.println(p);
                                                cEncontro = true;
                                                break;
                                            }
                                        }

                                        if (!cEncontro) {
                                            System.out.println("El premio [" + descPremio + "] no se encontró");
                                        }
                                        break;
                                }
                                break;
                            case "-pa":
                                switch (params[2]) {
                                    case "-i":
                                        int idParBus = Integer.parseInt(params[3]);

                                        for (Participante p : participantes) {
                                            if (p.getId() == idParBus) {
                                                System.out.println(p);
                                                break;
                                            }
                                        }

                                        break;
                                    case "-n":
                                        String nombre = params[3].toLowerCase().trim();

                                        for (Participante p : participantes) {
                                            if (p.getNombre().toLowerCase().contains(nombre)) {
                                                System.out.println(p);
                                                break;
                                            }
                                        }

                                        break;
                                }
                                break;

                            default:
                                help();
                        }
                    } catch (Exception e) {
                        help();
                    }
                    break;

                case "/mod":
                    try {
                        switch (params[1]) {
                            case "-pr":
                                int idPremio = Integer.parseInt(params[2]);
                                String[] p2;

                                for (Premio p : premios) {
                                    if (p.getId() == idPremio) {
                                        p2 = comando.split("\"");

                                        p.setPrecio(Integer.parseInt(p2[2].trim()));
                                        p.setDescripcion(p2[1]);

                                        break;
                                    }
                                }

                                System.out.println("El premio se actualizó");    
                                
                                break;

                            case "-pa":
                                int idPart = Integer.parseInt(params[2]);

                                for (Participante p : participantes) {
                                    if(p.getId() == idPart) {
                                        p2 = comando.split("\"");

                                        p.setNombre(p2[1]);
                                        p.setTelefono(p2[2].trim());

                                        break;
                                    }
                                }
                                
                                System.out.println("El Participante se actualizó"); 
                                
                                break;
                            default:
                                help();
                        }
                    } catch (Exception e) {
                        help();
                    }
                    break;

                case "/rifar":

                    if (cRifo) {
                        System.out.println("Ya cRifó xDxDxD jajaja");
                    } else {
                        int indexRandom;
                        for (Premio p : premios) {
                            indexRandom = r.nextInt(participantes.size());

                            Participante par = participantes.get(indexRandom);

                            System.out.println(par.getNombre() + " ha ganado "
                                    + p.getDescripcion() + " (Avaluado en "
                                    + p.getPrecio() + ")");
                        }
                        cRifo = true;
                    }

                    break;

                case "/est":
                    try {
                        switch (params[1]) {
                            case "-pro":
                                suma = 0;
                                for (Premio p : premios) {
                                    suma += p.getPrecio();
                                }

                                promedio = suma / premios.size();

                                System.out.println("PROMEDIO DE PRECIOS $:" + promedio);

                                break;
                            case "-sum":
                                suma = 0;
                                for (Premio p : premios) {
                                    suma += p.getPrecio();
                                }
                                System.out.println("La suma de precios es: $" + suma);
                                break;
                            default:
                                help();
                        }
                    } catch (Exception e) {
                        help();
                    }
                    break;

                case "/ayuda":
                case "/help":
                    help();
                    break;

                default:
                    System.out.println("Comando \"" + params[0] + "\" no se reconoce");

            }
        }
    }

    public void help() {
        System.out.println("/ayuda");
        System.out.println("\tEste comando muestra la lista actual de comandos");

        System.out.println("/salir");
        System.out.println("\tSale del terminal");

        System.out.println("/crear [-pr, -pa] [descripción o nombre] [precio ó telefono]");
        System.out.println("\tComando para crear premios o participantes");
        System.out.println("\t-pr: premios");
        System.out.println("\t-pa: participante");

        System.out.println("/listar [-pr, -pa, -t]");
        System.out.println("\tLista premios o participantes");
        System.out.println("\t-pr: premios");
        System.out.println("\t-pa: participante");
        System.out.println("\t-t: Todos");

        System.out.println("/buscar [-pr, -pa] [-i, -d, -n] [id , nombre, descripción]");
        System.out.println("\tComando para crear premios o participantes");
        System.out.println("\t-pr: premios");
        System.out.println("\t-pa: participante");

        System.out.println("/rifa");
        System.out.println("\tSortea la rifa");

        System.out.println("/mod");
        System.out.println("\tModifica los premios y participantes");

        System.out.println("/est [-pro, -sum]");
        System.out.println("\tComando para calcular estadísticas como:");
        System.out.println("\t-pro: promedio de precios de premios");
        System.out.println("\t-sum: suma de precios de premios");

    }

    private void crearTODO() {
        participantes.add(new Participante(1, "Marcelo", "+559-46546541"));
        participantes.add(new Participante(2, "Chino", "+569-8464654"));
        participantes.add(new Participante(3, "Pabli", "+569-6546465"));

        premios.add(new Premio(1, "Automovil", 5800000));
        premios.add(new Premio(2, "Casa", 1420000));
        premios.add(new Premio(3, "Cerro", 8549955));
    }

}
