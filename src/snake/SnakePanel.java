package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

public class SnakePanel extends JPanel implements Runnable  
{
	private static final long serialVersionUID = 1L;
	public static int W=410,H=310;
	Snake snake;
	public SnakePanel(Snake snake)
	{
		this.snake = snake;
		initComponents();
	}

	public void setSnake(Snake snake)
	{
		this.snake = snake;
	}
	private void initComponents()
	{
		setSize(W, H);
		validate();
	}

	public void paint(Graphics g)
	{
		Color c = getBackground();
		g.setColor(c);
		g.fillRect(0, 0, W, H);
		int x,y,w,h;
		w=Node.W;
		h=Node.H;
		Node node = snake.food;
		x=node.x;
		y=node.y;
		g.setColor(Color.blue);
		g.fillRect(x, y, w, h);
		Vector<Node> snakeBody = snake.snakeBody;
		int len = snakeBody.size();
		for (int i=0; i<len; i++)
		{
			node = snakeBody.get(i);
			x=node.x;
			y=node.y;
			if (i%2 == 0)
			{
				g.setColor(Color.red);
			}
			else
			{
				g.setColor(Color.black);
			}
			g.fillRect(x, y, w, h);
		}
		String str = null;
		if (snake.SPEED==SnakeFrame.SLOW)
		{
			str="初级";
		}
		else if(snake.SPEED == SnakeFrame.MID)
		{
			str="中级";
		}
		else if(snake.SPEED==SnakeFrame.FAST)
		{
			str="高级";
		}
		g.drawString("当前等级："+str,180,20);
		g.drawString("当前蛇长："+Integer.toString(len), 180, 40);
//		System.out.println("当前蛇长：" +len);
	}
	public void run()
	{
		while (true)
		{
			if (snake.isLive())
			{
				repaint();
				if (snake.isRun())
				{
					snake.move();
					//System.out.print("我执行了");
				}
				try
				{
					Thread.sleep(snake.SPEED);
				}
				catch (InterruptedException e)
				{
					throw new RuntimeException(e);
				}
			}
			else
			{
				System.out.print("GAME OVER!");
				break;
			}
		}
	}

}
