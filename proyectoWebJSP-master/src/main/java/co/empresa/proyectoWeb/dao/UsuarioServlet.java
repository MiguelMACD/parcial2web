package co.empresa.proyectoWeb.dao

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tu.paquete.dao.UsuarioDao;
import tu.paquete.modelo.Usuario;

@WebServlet("/")
public class UsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDao usuarioDao;

    public UsuarioServlet() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        this.usuarioDao = new UsuarioDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/list":
                    listUsuarios(request, response);
                    break;
                case "/delete":
                    eliminarUsuario(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    actualizarUsuario(request, response);
                    break;
                default:
                    listUsuarios(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Usuario> listUsuarios = usuarioDao.selectAll();
        request.setAttribute("listUsuarios", listUsuarios);

        RequestDispatcher dispatcher = request.getRequestDispatcher("usuariolist.jsp");
        dispatcher.forward(request, response);
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioDao.delete(id);
        response.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuarioActual = usuarioDao.select(id);
        request.setAttribute("usuario", usuarioActual);

        RequestDispatcher dispatcher = request.getRequestDispatcher("usuario.jsp");
        dispatcher.forward(request, response);
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String pais = request.getParameter("pais");

        Usuario usuario = new Usuario(id, nombre, email, pais);

        usuarioDao.update(usuario);

        response.sendRedirect("list");
    }
}
