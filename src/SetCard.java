/*  card for set */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.TexturePaint;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
// for Graphics and Container
// for Gradient

/**
 * This creates a card for the 'Set' game (and can draw it)
 * 
 * @author Steve Harper
 * @author Justin Courville
 * @author Matt Heinze
 * @version 1.2
 */

public class SetCard extends JComponent implements MouseListener {
	// Primitive integer based card values
	public int color;
	public int shape;
	public int fill;
	public int denomination;
	boolean mouseActive = true;
	// Variables for painting
	final Dimension preferredSize;
	int cardClicked;
	boolean cardSelected;
	boolean cpuCardSelected;
	BasicStroke basicPen = new BasicStroke(5.0f);
	BasicStroke pen = new BasicStroke(2.0f);
	BufferedImage texture;
	TexturePaint textureTp;

	// SetCard Constructor
	public SetCard(int myColor, int myshape, int myfill, int numberOfShapes,
			int shortSideSize) {
		cardClicked = 2;
		color = myColor;
		shape = myshape;
		fill = myfill;
		denomination = numberOfShapes;
		preferredSize = new Dimension(shortSideSize, shortSideSize
				+ shortSideSize / 3);
		cardSelected = false;
		addMouseListener(this);

	}

	// Set the border color of the card to be red or black
	public void determineCardClicked(Graphics2D g2) {

		if (cardClicked % 2 != 0) {
			g2.setColor(Color.red);
			cardSelected = true;
			cpuCardSelected = false;
		}

		if (cardClicked % 2 == 0) {
			g2.setStroke(basicPen);
			g2.setColor(Color.black);
			// cpuCardSelected = false;
			cardSelected = false;
		}
		if (cpuCardSelected == true) {
			g2.setColor(Color.yellow);
			cpuCardSelected = true;
			cardSelected = false;
		}

	}

	// Set the border color of the card to be red or black
	public void determineCpuCardSelected(Graphics2D g2) {
		if (cpuCardSelected == false) {
			g2.setStroke(basicPen);
			g2.setColor(Color.black);
			cpuCardSelected = false;
		}
		if (cpuCardSelected == true) {
			g2.setColor(Color.yellow);
			cpuCardSelected = true;
		}
	}

	@Override
	public void paint(Graphics g) {
		// Local variables used for painting
		Graphics2D g2 = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();
		Paint paint = g2.getPaint();

		// Stroke used initially to draw card outline
		g2.setStroke(basicPen);

		// Determine whether the card has been clicked on and selected or not
		// determineCpuCardSelected(g2);
		determineCardClicked(g2);

		// Draw and fill the basic card outline
		g2.drawRoundRect(0, 0, width - 5, height - 5, 10, 10);
		// Paint original = g2.getPaint();
		g2.setColor(Color.getHSBColor(0, 0, 25));
		GradientPaint graytowhite = new GradientPaint(0, 0, Color.gray, 100, 0,
				Color.WHITE);
		g2.setPaint(graytowhite);

		g2.fillRoundRect(2, 2, width - 7, height - 7, 10, 10);
		g2.setPaint(paint);

		// Set the stroke back to something lighter in width to draw the shapes
		g2.setStroke(pen);
		if (color == 1) {
			g2.setColor(Color.blue);
			GradientPaint gradientPaint = new GradientPaint(0, 0, Color.white,
					width * 2, height * 2, Color.blue);
			if (shape == 1) {
				if (fill == 1) {

					if (denomination == 1) {
						g2.setStroke(pen);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));

					}
					if (denomination == 2) {
						g2.setStroke(pen);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 4, width / 2, height / 8, 10, 10)));

					}
					if (denomination == 3) {
						g2.setStroke(pen);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 7, width / 2, height / 8, 10, 10)));
						g2.draw((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 12, width / 2, height / 8, 10,
								10)));
						g2.draw((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 3, width / 2, height / 8, 10, 10)));

					}

				}
				if (fill == 2) {
					if (denomination == 1) {
						g2.setPaint(gradientPaint);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.blue);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
					}
					if (denomination == 2) {
						g2.setPaint(gradientPaint);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 4, width / 2, height / 8, 10, 10)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.blue);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 4, width / 2, height / 8, 10, 10)));

					}
					if (denomination == 3) {
						g2.setPaint(gradientPaint);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 7, width / 2, height / 8, 10, 10)));
						g2.fill((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 12, width / 2, height / 8, 10,
								10)));
						g2.fill((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 3, width / 2, height / 8, 10, 10)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.blue);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 7, width / 2, height / 8, 10, 10)));
						g2.draw((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 12, width / 2, height / 8, 10,
								10)));
						g2.draw((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 3, width / 2, height / 8, 10, 10)));

					}

				}
				if (fill == 3) {
					if (denomination == 1) {

						g2.setPaint(paint);
						g2.setColor(Color.blue);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));

					}
					if (denomination == 2) {

						g2.setPaint(paint);
						g2.setColor(Color.blue);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 4, width / 2, height / 8, 10, 10)));

					}
					if (denomination == 3) {
						g2.setPaint(paint);
						g2.setColor(Color.blue);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 7, width / 2, height / 8, 10, 10)));
						g2.fill((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 12, width / 2, height / 8, 10,
								10)));
						g2.fill((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 3, width / 2, height / 8, 10, 10)));

					}

				}
			}
			if (shape == 2) {
				if (fill == 1) {
					if (denomination == 1) {
						g2.draw(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
					}
					if (denomination == 2) {
						g2.draw(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 4,
								width / 2, height / 8));
					}
					if (denomination == 3) {
						g2.draw(new Ellipse2D.Double(width / 4, height / 7,
								width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 3
								+ height / 12, width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 3
								+ height / 3, width / 2, height / 8));
					}

				}
				if (fill == 2) {
					if (denomination == 1) {
						g2.setPaint(gradientPaint);
						g2.fill(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.blue);
						g2.draw(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
					}
					if (denomination == 2) {
						g2.setPaint(gradientPaint);
						g2.fill(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 4,
								width / 2, height / 8));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.blue);
						g2.draw(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 4,
								width / 2, height / 8));
					}
					if (denomination == 3) {
						g2.setPaint(gradientPaint);
						g2.fill(new Ellipse2D.Double(width / 4, height / 7,
								width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 3
								+ height / 12, width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 3
								+ height / 3, width / 2, height / 8));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.blue);
						g2.draw(new Ellipse2D.Double(width / 4, height / 7,
								width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 3
								+ height / 12, width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 3
								+ height / 3, width / 2, height / 8));
					}

				}
				if (fill == 3) {
					if (denomination == 1) {
						g2.setPaint(paint);
						g2.setColor(Color.blue);
						g2.fill(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
					}
					if (denomination == 2) {
						g2.setPaint(paint);
						g2.setColor(Color.blue);
						g2.fill(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 4,
								width / 2, height / 8));
					}
					if (denomination == 3) {
						g2.setPaint(paint);
						g2.setColor(Color.blue);
						g2.fill(new Ellipse2D.Double(width / 4, height / 7,
								width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 3
								+ height / 12, width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 3
								+ height / 3, width / 2, height / 8));
					}

				}
			}
			if (shape == 3) {
				if (fill == 1) {
					if (denomination == 1) {
						g2.draw((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
					}
					if (denomination == 2) {
						g2.draw((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 4,
								height / 6, height / 6)));
					}
					if (denomination == 3) {
						g2.draw((new Rectangle2D.Double(width / 3, height / 7,
								height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 3
								+ height / 12, height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 3
								+ height / 3, height / 6, height / 6)));
					}

				}
				if (fill == 2) {
					if (denomination == 1) {
						g2.setPaint(gradientPaint);
						g2.fill((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.blue);
						g2.draw((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));

					}
					if (denomination == 2) {
						g2.setPaint(gradientPaint);
						g2.fill((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 4,
								height / 6, height / 6)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.blue);
						g2.draw((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 4,
								height / 6, height / 6)));
					}
					if (denomination == 3) {
						g2.setPaint(gradientPaint);
						g2.fill((new Rectangle2D.Double(width / 3, height / 7,
								height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 3
								+ height / 12, height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 3
								+ height / 3, height / 6, height / 6)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.blue);
						g2.draw((new Rectangle2D.Double(width / 3, height / 7,
								height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 3
								+ height / 12, height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 3
								+ height / 3, height / 6, height / 6)));

					}

				}
				if (fill == 3) {
					if (denomination == 1) {
						g2.setPaint(paint);
						g2.setColor(Color.blue);
						g2.fill((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
					}
					if (denomination == 2) {
						g2.setPaint(paint);
						g2.setColor(Color.blue);
						g2.fill((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 4,
								height / 6, height / 6)));
					}
					if (denomination == 3) {
						g2.setPaint(paint);
						g2.setColor(Color.blue);
						g2.fill((new Rectangle2D.Double(width / 3, height / 7,
								height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 3
								+ height / 12, height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 3
								+ height / 3, height / 6, height / 6)));
					}

				}

			}
		}

		if (color == 2) {
			g2.setColor(Color.RED);
			GradientPaint gradientPaint = new GradientPaint(0, 0, Color.white,
					width * 2, height * 2, Color.RED);
			if (shape == 1) {
				if (fill == 1) {

					if (denomination == 1) {
						g2.setStroke(pen);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));

					}
					if (denomination == 2) {
						g2.setStroke(pen);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 4, width / 2, height / 8, 10, 10)));

					}
					if (denomination == 3) {
						g2.setStroke(pen);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 7, width / 2, height / 8, 10, 10)));
						g2.draw((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 12, width / 2, height / 8, 10,
								10)));
						g2.draw((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 3, width / 2, height / 8, 10, 10)));

					}

				}
				if (fill == 2) {
					if (denomination == 1) {
						g2.setPaint(gradientPaint);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.RED);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
					}
					if (denomination == 2) {
						g2.setPaint(gradientPaint);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 4, width / 2, height / 8, 10, 10)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.RED);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 4, width / 2, height / 8, 10, 10)));

					}
					if (denomination == 3) {
						g2.setPaint(gradientPaint);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 7, width / 2, height / 8, 10, 10)));
						g2.fill((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 12, width / 2, height / 8, 10,
								10)));
						g2.fill((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 3, width / 2, height / 8, 10, 10)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.RED);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 7, width / 2, height / 8, 10, 10)));
						g2.draw((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 12, width / 2, height / 8, 10,
								10)));
						g2.draw((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 3, width / 2, height / 8, 10, 10)));

					}

				}
				if (fill == 3) {
					if (denomination == 1) {

						g2.setPaint(paint);
						g2.setColor(Color.RED);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));

					}
					if (denomination == 2) {

						g2.setPaint(paint);
						g2.setColor(Color.RED);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 4, width / 2, height / 8, 10, 10)));

					}
					if (denomination == 3) {
						g2.setPaint(paint);
						g2.setColor(Color.RED);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 7, width / 2, height / 8, 10, 10)));
						g2.fill((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 12, width / 2, height / 8, 10,
								10)));
						g2.fill((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 3, width / 2, height / 8, 10, 10)));

					}

				}
			}
			if (shape == 2) {
				if (fill == 1) {
					if (denomination == 1) {
						g2.draw(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
					}
					if (denomination == 2) {
						g2.draw(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 4,
								width / 2, height / 8));
					}
					if (denomination == 3) {
						g2.draw(new Ellipse2D.Double(width / 4, height / 7,
								width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 3
								+ height / 12, width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 3
								+ height / 3, width / 2, height / 8));
					}

				}
				if (fill == 2) {
					if (denomination == 1) {
						g2.setPaint(gradientPaint);
						g2.fill(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.RED);
						g2.draw(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
					}
					if (denomination == 2) {
						g2.setPaint(gradientPaint);
						g2.fill(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 4,
								width / 2, height / 8));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.RED);
						g2.draw(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 4,
								width / 2, height / 8));
					}
					if (denomination == 3) {
						g2.setPaint(gradientPaint);
						g2.fill(new Ellipse2D.Double(width / 4, height / 7,
								width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 3
								+ height / 12, width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 3
								+ height / 3, width / 2, height / 8));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.RED);
						g2.draw(new Ellipse2D.Double(width / 4, height / 7,
								width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 3
								+ height / 12, width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 3
								+ height / 3, width / 2, height / 8));
					}

				}
				if (fill == 3) {
					if (denomination == 1) {
						g2.setPaint(paint);
						g2.setColor(Color.RED);
						g2.fill(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
					}
					if (denomination == 2) {
						g2.setPaint(paint);
						g2.setColor(Color.RED);
						g2.fill(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 4,
								width / 2, height / 8));
					}
					if (denomination == 3) {
						g2.setPaint(paint);
						g2.setColor(Color.RED);
						g2.fill(new Ellipse2D.Double(width / 4, height / 7,
								width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 3
								+ height / 12, width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 3
								+ height / 3, width / 2, height / 8));
					}

				}
			}
			if (shape == 3) {
				if (fill == 1) {
					if (denomination == 1) {
						g2.draw((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
					}
					if (denomination == 2) {
						g2.draw((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 4,
								height / 6, height / 6)));
					}
					if (denomination == 3) {
						g2.draw((new Rectangle2D.Double(width / 3, height / 7,
								height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 3
								+ height / 12, height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 3
								+ height / 3, height / 6, height / 6)));
					}

				}
				if (fill == 2) {
					if (denomination == 1) {
						g2.setPaint(gradientPaint);
						g2.fill((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.RED);
						g2.draw((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));

					}
					if (denomination == 2) {
						g2.setPaint(gradientPaint);
						g2.fill((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 4,
								height / 6, height / 6)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.RED);
						g2.draw((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 4,
								height / 6, height / 6)));
					}
					if (denomination == 3) {
						g2.setPaint(gradientPaint);
						g2.fill((new Rectangle2D.Double(width / 3, height / 7,
								height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 3
								+ height / 12, height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 3
								+ height / 3, height / 6, height / 6)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.RED);
						g2.draw((new Rectangle2D.Double(width / 3, height / 7,
								height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 3
								+ height / 12, height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 3
								+ height / 3, height / 6, height / 6)));

					}

				}
				if (fill == 3) {
					if (denomination == 1) {
						g2.setPaint(paint);
						g2.setColor(Color.RED);
						g2.fill((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
					}
					if (denomination == 2) {
						g2.setPaint(paint);
						g2.setColor(Color.RED);
						g2.fill((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 4,
								height / 6, height / 6)));
					}
					if (denomination == 3) {
						g2.setPaint(paint);
						g2.setColor(Color.RED);
						g2.fill((new Rectangle2D.Double(width / 3, height / 7,
								height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 3
								+ height / 12, height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 3
								+ height / 3, height / 6, height / 6)));
					}

				}

			}
		}
		if (color == 3) {
			g2.setColor(Color.GREEN);
			GradientPaint gradientPaint = new GradientPaint(0, 0, Color.white,
					width * 2, height * 2, Color.GREEN);
			if (shape == 1) {
				if (fill == 1) {

					if (denomination == 1) {
						g2.setStroke(pen);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));

					}
					if (denomination == 2) {
						g2.setStroke(pen);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 4, width / 2, height / 8, 10, 10)));

					}
					if (denomination == 3) {
						g2.setStroke(pen);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 7, width / 2, height / 8, 10, 10)));
						g2.draw((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 12, width / 2, height / 8, 10,
								10)));
						g2.draw((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 3, width / 2, height / 8, 10, 10)));

					}

				}
				if (fill == 2) {
					if (denomination == 1) {
						g2.setPaint(gradientPaint);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.GREEN);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
					}
					if (denomination == 2) {
						g2.setPaint(gradientPaint);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 4, width / 2, height / 8, 10, 10)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.GREEN);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 4, width / 2, height / 8, 10, 10)));

					}
					if (denomination == 3) {
						g2.setPaint(gradientPaint);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 7, width / 2, height / 8, 10, 10)));
						g2.fill((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 12, width / 2, height / 8, 10,
								10)));
						g2.fill((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 3, width / 2, height / 8, 10, 10)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.GREEN);
						g2.draw((new RoundRectangle2D.Double(width / 4,
								height / 7, width / 2, height / 8, 10, 10)));
						g2.draw((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 12, width / 2, height / 8, 10,
								10)));
						g2.draw((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 3, width / 2, height / 8, 10, 10)));

					}

				}
				if (fill == 3) {
					if (denomination == 1) {

						g2.setPaint(paint);
						g2.setColor(Color.GREEN);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));

					}
					if (denomination == 2) {

						g2.setPaint(paint);
						g2.setColor(Color.GREEN);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 2, width / 2, height / 8, 10, 10)));
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 4, width / 2, height / 8, 10, 10)));

					}
					if (denomination == 3) {
						g2.setPaint(paint);
						g2.setColor(Color.GREEN);
						g2.fill((new RoundRectangle2D.Double(width / 4,
								height / 7, width / 2, height / 8, 10, 10)));
						g2.fill((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 12, width / 2, height / 8, 10,
								10)));
						g2.fill((new RoundRectangle2D.Double(width / 4, height
								/ 3 + height / 3, width / 2, height / 8, 10, 10)));

					}

				}
			}
			if (shape == 2) {
				if (fill == 1) {
					if (denomination == 1) {
						g2.draw(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
					}
					if (denomination == 2) {
						g2.draw(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 4,
								width / 2, height / 8));
					}
					if (denomination == 3) {
						g2.draw(new Ellipse2D.Double(width / 4, height / 7,
								width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 3
								+ height / 12, width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 3
								+ height / 3, width / 2, height / 8));
					}

				}
				if (fill == 2) {
					if (denomination == 1) {
						g2.setPaint(gradientPaint);
						g2.fill(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.GREEN);
						g2.draw(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
					}
					if (denomination == 2) {
						g2.setPaint(gradientPaint);
						g2.fill(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 4,
								width / 2, height / 8));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.GREEN);
						g2.draw(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 4,
								width / 2, height / 8));
					}
					if (denomination == 3) {
						g2.setPaint(gradientPaint);
						g2.fill(new Ellipse2D.Double(width / 4, height / 7,
								width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 3
								+ height / 12, width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 3
								+ height / 3, width / 2, height / 8));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.GREEN);
						g2.draw(new Ellipse2D.Double(width / 4, height / 7,
								width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 3
								+ height / 12, width / 2, height / 8));
						g2.draw(new Ellipse2D.Double(width / 4, height / 3
								+ height / 3, width / 2, height / 8));
					}

				}
				if (fill == 3) {
					if (denomination == 1) {
						g2.setPaint(paint);
						g2.setColor(Color.GREEN);
						g2.fill(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
					}
					if (denomination == 2) {
						g2.setPaint(paint);
						g2.setColor(Color.GREEN);
						g2.fill(new Ellipse2D.Double(width / 4, height / 2,
								width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 4,
								width / 2, height / 8));
					}
					if (denomination == 3) {
						g2.setPaint(paint);
						g2.setColor(Color.GREEN);
						g2.fill(new Ellipse2D.Double(width / 4, height / 7,
								width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 3
								+ height / 12, width / 2, height / 8));
						g2.fill(new Ellipse2D.Double(width / 4, height / 3
								+ height / 3, width / 2, height / 8));
					}

				}
			}
			if (shape == 3) {
				if (fill == 1) {
					if (denomination == 1) {
						g2.draw((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
					}
					if (denomination == 2) {
						g2.draw((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 4,
								height / 6, height / 6)));
					}
					if (denomination == 3) {
						g2.draw((new Rectangle2D.Double(width / 3, height / 7,
								height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 3
								+ height / 12, height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 3
								+ height / 3, height / 6, height / 6)));
					}

				}
				if (fill == 2) {
					if (denomination == 1) {
						g2.setPaint(gradientPaint);
						g2.fill((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.GREEN);
						g2.draw((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));

					}
					if (denomination == 2) {
						g2.setPaint(gradientPaint);
						g2.fill((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 4,
								height / 6, height / 6)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.GREEN);
						g2.draw((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 4,
								height / 6, height / 6)));
					}
					if (denomination == 3) {
						g2.setPaint(gradientPaint);
						g2.fill((new Rectangle2D.Double(width / 3, height / 7,
								height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 3
								+ height / 12, height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 3
								+ height / 3, height / 6, height / 6)));
						g2.setPaint(paint);
						g2.setStroke(pen);
						g2.setColor(Color.GREEN);
						g2.draw((new Rectangle2D.Double(width / 3, height / 7,
								height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 3
								+ height / 12, height / 6, height / 6)));
						g2.draw((new Rectangle2D.Double(width / 3, height / 3
								+ height / 3, height / 6, height / 6)));

					}

				}
				if (fill == 3) {
					if (denomination == 1) {
						g2.setPaint(paint);
						g2.setColor(Color.GREEN);
						g2.fill((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
					}
					if (denomination == 2) {
						g2.setPaint(paint);
						g2.setColor(Color.GREEN);
						g2.fill((new Rectangle2D.Double(width / 3, height / 2,
								height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 4,
								height / 6, height / 6)));
					}
					if (denomination == 3) {
						g2.setPaint(paint);
						g2.setColor(Color.GREEN);
						g2.fill((new Rectangle2D.Double(width / 3, height / 7,
								height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 3
								+ height / 12, height / 6, height / 6)));
						g2.fill((new Rectangle2D.Double(width / 3, height / 3
								+ height / 3, height / 6, height / 6)));
					}

				}

			}
		}

		// g2.drawString(""+color+""+shape+""+""+fill+""+denomination+""+cardSelected,
		// width/4,height/2);

		// g2.setStroke(pen);
		// g2.draw(new RoundRectangle2D.Double(width/4, height/2, 20, 20, 10,
		// 10));

	}

	public void determineColor(Graphics g2, int width, int height) {
		if (color == 1) {
			g2.setColor(Color.blue);

			GradientPaint gradientPaint = new GradientPaint(0, 0, Color.white,
					width / 2, height / 2, Color.blue);
		}
		if (color == 2) {
			g2.setColor(Color.red);
		}
		if (color == 3) {
			g2.setColor(Color.green);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return preferredSize;
	}

	@Override
	public Dimension getMinimumSize() {
		return preferredSize;
	}

	@Override
	public Dimension getMaximumSize() {

		return preferredSize;

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (mouseActive == true) {

			cardClicked++;

			revalidate(); // this GUI needs relayout
			repaint();
		}
		if (mouseActive == false) {
			// do nothing
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean getCardSelected() {
		return cardSelected;
	}

	public int getColor() {
		return color;
	}

	public int getCardClicked() {
		return cardClicked;
	}

	public void setCardSelected(Boolean passedInBoolean) {
		cardSelected = passedInBoolean;
	}

	public void setCardClicked(int passedInInt) {
		cardClicked = passedInInt;
	}

	public void setCpuCardSelected(Boolean passedInBoolean) {
		cpuCardSelected = passedInBoolean;
	}

	public void setMouseActive(Boolean passedInBoolean) {
		mouseActive = passedInBoolean;
	}

}
