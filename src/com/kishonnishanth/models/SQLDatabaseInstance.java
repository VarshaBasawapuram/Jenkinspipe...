package com.kishonnishanth.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kishonnishant.cartcontrollers.Product;
import com.kishonnishant.cartcontrollers.User;

public class SQLDatabaseInstance {
	private Properties prop = null;
	private InputStream input = null;
	private String url = null;
	private String userName = null;
	private String password = null;
	private Connection conn = null;
	private Statement statement = null;
	private static SQLDatabaseInstance sqlInstance = null;

	private SQLDatabaseInstance(int noProps) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost/cartinfo", "root", "Mucherlas1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.statement = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static SQLDatabaseInstance getSQLDBI() {
		if (sqlInstance == null) {
			sqlInstance = new SQLDatabaseInstance(1);
			return sqlInstance;
		}
		return sqlInstance;
	}

	public SQLDatabaseInstance() {
		prop = new Properties();
		try {
			input = new FileInputStream("../CartApp/Config/config.properties");
		} catch (FileNotFoundException FNFE) {
			// TODO Auto-generated catch block
			FNFE.printStackTrace();
		}

		try {
			// load a properties file
			prop.load(input);

			// get the property value and print it out
			// System.out.println(prop.getProperty("database"));
			// System.out.println(prop.getProperty("dbuser"));
			// System.out.println(prop.getProperty("dbpassword"));
			url = prop.getProperty("dbURL");
			userName = prop.getProperty("dbUserName");
			password = prop.getProperty("dbPassword");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @return the prop
	 */
	public Properties getProp() {
		return prop;
	}

	/**
	 * @return the input
	 */
	public InputStream getInput() {
		return input;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	private Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DriverManager.getConnection(this.getUrl(), this.getUserName(),
			// this.getPassword());
			conn = DriverManager.getConnection(this.getUrl(), this.getUserName(), this.getPassword());
		} catch (Exception e) {
			System.out.println(e);
		}

		return conn;
	}

	private Statement getStatement() {
		try {
			statement = this.getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statement;
	}

	public boolean closeAllConnections() {
		return true;
	}

	public boolean checkUserInDB(String email, String pass) {
		if (statement == null) {
			statement = this.getStatement();
		}
		String query = "SELECT * FROM cartinfo.users WHERE email = '" + email + "' and password = '" + pass + "'";
		try {
			if (!this.statement.executeQuery(query).isBeforeFirst()) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean addUserToDB(User user) {
		if (statement == null) {
			statement = this.getStatement();
		}
		String query = "INSERT INTO CARTINFO.USERS VALUES('" + user.getUserName() + "','" + user.getEmail() + "','"
				+ user.getPassword() + "','" + user.getPhoneNumber() + "')";
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Product> getAllProductsDetails() {
		try {
			ResultSet res = this.getStatement().executeQuery("SELECT * FROM cartinfo.products");
			List<Product> products = new ArrayList<>();
			while (res.next()) {
				Product product = new Product();
				product.setProdId(res.getInt(1));
				product.setProdName(res.getString(2));
				product.setProdURL(res.getString(3));
				product.setProdDescription(res.getString(5));
				products.add(product);
			}
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In here");
			e.printStackTrace();
			return null;
		}

	}

	public Map<Integer, Integer> getCartListByUser(String emailId) {
		Map<Integer, Integer> userCartDetails = new HashMap<Integer, Integer>();
		String query = "SELECT productId, quantity FROM cartinfo.cart where email='" + emailId + "'";
		try {
			ResultSet rs = statement.executeQuery(query);
			System.out.println(rs);
			while (rs.next()) {
				userCartDetails.put(rs.getInt(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userCartDetails;
	}

	public List<Integer> getWishListByUser(String emailId) {
		List<Integer> userWishList = new ArrayList<Integer>();
		String query = "SELECT productId FROM cartinfo.wishlist where email ='" + emailId + "'";
		try {
			ResultSet rsWish = statement.executeQuery(query);
			while (rsWish.next()) {
				int i = (rsWish.getInt(1));
				userWishList.add(i);
				System.out.println("Hehe");
				System.out.println(rsWish.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userWishList;
	}

	public int getProductQuantityByUser(String email, int productId) {
		int quantity = 0;
		String quantityQuery = "SELECT quantity FROM cartinfo.cart where email='" + email + "'and productId="
				+ productId + "";
		try {
			ResultSet rs = statement.executeQuery(quantityQuery);
			rs.next();
			quantity = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quantity;
	}

	public boolean addToCart(String emailID, int productID) {
		String query = "Select productId, quantity, email from cartinfo.cart where email ='" + emailID
				+ "'and productId=" + productID + "";
		String email = emailID;
		int prod = productID;
		int productIdOne;
		try {
			ResultSet rs = statement.executeQuery(query);
			if (rs.isBeforeFirst()) {
				rs.next();
				productIdOne = rs.getInt(1);
				if (productID == productIdOne) {
					System.out.println("product already exists");

					int quantity = this.getProductQuantityByUser(email, prod) + 1;
					String queryUpdate = "Update cart SET quantity = " + quantity + " where email='" + email
							+ "'and productId=" + productID + "";
					statement.executeUpdate(queryUpdate);
					return true;
				}
			} else {
				String queryInsert = "Insert into cart values('" + email + "'," + prod + ",1)";
				statement.executeUpdate(queryInsert);
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Product getProductById(int productId) {
		String query = "Select name,description,url from cartinfo.products where productId=" + productId + "";
		Product product = null;
		try {
			String name, description, url;
			product = new Product();
			ResultSet re = statement.executeQuery(query);
			System.out.println("Name\t\t Description\t url");
			if (re.next()) {
				product.setProdDescription(re.getString(2));
				product.setProdId(productId);
				product.setProdName(re.getString(1));
				product.setProdURL(re.getString(3));
				name = re.getString(1);
				description = re.getString(2);
				url = re.getString(3);
				System.out.println(name + "\t\t" + description + "\t" + url);
				System.out.println(name +" "+ description+" "+ " "+url );
			} else {
				System.out.println("result not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	public boolean addToWishList(String emailID, int productID) {
		String query = "Select productId, email from cartinfo.wishList where email='" + emailID + "'and productId="
				+ productID + "";
		int productIdOne;
		try {
			ResultSet rs = statement.executeQuery(query);
			if (rs.isBeforeFirst()) {
				rs.next();
				productIdOne = rs.getInt(1);
				if (productID == productIdOne) {
					System.out.println("product already exists");
				}

			} else {
				System.out.println("In here");
				String queryInsert = "Insert into wishList values(" + productID + ",'" + emailID + "')";
				statement.executeUpdate(queryInsert);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean deletePoroductInWishList(String emailId,int prodctId) {
		String sql = "DELETE from wishList where email='"+emailId+"' and productId ="+prodctId;
		try {
			this.statement.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String... args) {
		SQLDatabaseInstance sq = new SQLDatabaseInstance(1);
		System.out.println(sq);
		List<Product> products = sq.getAllProductsDetails();
		for (Product prod : products) {
			System.out.println(prod.getProdId());

		}
	}
}
