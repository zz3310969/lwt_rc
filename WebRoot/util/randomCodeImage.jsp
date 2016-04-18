<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="image/jpeg"
	import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*"%>
	
<%!Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}%>
<%
	int RandomWidth = 90;
	int RandomHeight = 24;

	String[] bytes = new String[] { "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "0", "a", "b", "c", "d", "e", "f", "g", "h",
			"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
			"u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J", "K", "M", "L", "N", "O", "P", "Q", "R",
			"S", "T", "U", "V", "W", "X", "Y", "Z" };

	BufferedImage buffImg = new BufferedImage(RandomWidth,
			RandomHeight, BufferedImage.TYPE_INT_RGB);

	Graphics2D g = buffImg.createGraphics();

	Random random = new Random();

	g.setColor(Color.WHITE);
	g.fillRect(0, 0, RandomWidth, RandomHeight);

	Font font = new Font("Times New Roman", Font.BOLD, 20);
	g.setFont(font);

	g.setColor(Color.BLACK);
	g.drawRect(0, 0, RandomWidth - 1, RandomHeight - 1);

	g.setColor(Color.gray);

	for (int i = 0; i < 80; i++) {
		int x = random.nextInt(RandomWidth);
		int y = random.nextInt(RandomHeight);
		int xl = random.nextInt(12);
		int yl = random.nextInt(12);
		g.drawLine(x, y, x + xl, y + yl);
	}

	StringBuffer randomCode = new StringBuffer();
	int red = 0, green = 0, blue = 0;
	String strRand = "";
	for (int i = 0; i < 5; i++) {
		strRand = String
				.valueOf(bytes[random.nextInt(bytes.length - 1)]);
		red = random.nextInt(110);
		green = random.nextInt(50);
		blue = random.nextInt(50);
		g.setColor(new Color(red, green, blue));
		g.drawString(strRand, 15 * i + 2, 16);
		randomCode.append(strRand);
	}
	session = request.getSession();
	session.setAttribute("systemRandomCode", randomCode.toString());

	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setContentType("image/jpeg");
	ServletOutputStream sos = response.getOutputStream();
	ImageIO.write(buffImg, "jpeg", sos);

	sos.close();
	out.clear();
	out = pageContext.pushBody();
%>



