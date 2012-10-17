package snake;

import java.util.Vector;

import javax.swing.JOptionPane;

public class Snake
{
	public Vector<Node> snakeBody = new Vector<Node>(0,1);
	public Node food;
	public static int UP=1,RIGHT=2,DOWN=3,LEFT=4;
	public int direction = Snake.RIGHT;
	public int SPEED = SnakeFrame.SLOW;
	boolean state=false;
	boolean life=true;
	Snake()
	{
		createFood();
		int w = SnakePanel.W/Node.W,
			h = SnakePanel.H/Node.H;
		int x,y;
		x = Node.W*(int)(w/2);
		y = Node.H*(int)(h/2);
		snakeBody.add(new Node(x+Node.W*2,y));//注意添加顺序必须和初始的方向问题
		snakeBody.add(new Node(x+Node.W,y));
		snakeBody.add(new Node(x,y));
	}
	public void createFood()
	{
		int x,y;
		int w = SnakePanel.W/Node.W-1,
			h = SnakePanel.H/Node.H-3;
		x = (int)(Math.random()*w);
		y = (int)(Math.random()*h);
		int len = snakeBody.size();
		boolean isOKxy=true;
		for (int i=0; i<len; i++)
		{
			Node node = snakeBody.get(i);
			if (x==node.x && y==node.y)
			{
				isOKxy=false;
				break;
			}
		}
		if (isOKxy)
		{
			food = new Node(x*Node.W,y*Node.H);
		}
		else
		{
			createFood();
		}
	}
	public void move()
	{
		if (isEaten())
		{
			snakeBody.add(0,food);
			createFood();
			return;
		}
		int len = snakeBody.size();
		Node endNode = snakeBody.remove(len-1);//记录最后一个节点，如果蛇死了，就补回最后一个节点
		int x,y;
		Node headNode = snakeBody.get(0);
		x = headNode.x;
		y = headNode.y;
		switch (direction)
		{
		case 2:
			x = headNode.x+Node.W;
			break;
		case 4:
			x = headNode.x - Node.W;
			break;
		case 1:
			y = headNode.y-Node.H;
			break;
		case 3:
			y = headNode.y + Node.H;
			break;
		}
		Node node = new Node(x,y);
		if (isDie(node))
		{
			life = false;
			System.out.println("GAME OVER!");
			JOptionPane.showMessageDialog(null, "GAME OVER!", "GAME OVER!", JOptionPane.INFORMATION_MESSAGE);
			state=false;
			snakeBody.add(0,endNode);
			return;
		}
		snakeBody.add(0,node);
	}
	public boolean isDie(Node node)//查看头节点是否出界或者在蛇身里面
	{
		int x,y;
		x = node.x;
		y = node.y;
		//System.out.println("node.x="+x);
		//System.out.println("node.y="+y);
		if (x>=SnakePanel.W-Node.W || x<0 || y<0 || y>=SnakePanel.H-3*Node.H)
		{
			return true;
		}
		int len = snakeBody.size();
		for (int i=0; i<len; i++)
		{
			Node subNode = snakeBody.get(i);
			if (x == subNode.x && y==subNode.y)
			{
				//System.out.println("in here:i="+i);
				//System.out.println("sunode:x="+subNode.x);
				//System.out.println("sunode:y="+subNode.y);
				return true;
			}
		}
		return false;
	}
	public void changeDirection(int dir)
	{
		if ((direction%2 != 0 && dir%2 == 0)
			|| (direction%2 == 0 && dir%2 != 0))
		{
			direction = dir;
		}
	}
	public void pause()
	{
		state = !state;
	}
	public boolean isRun()
	{
		return state;
	}
	public boolean isLive()
	{
		return life;
	}
	public boolean isEaten()
	{
		Node headNode = snakeBody.get(0);
		int x=headNode.x;
		int y=headNode.y;
		int fx = food.x;
		int fy = food.y;
		if ((direction == Snake.RIGHT && x+Node.W==fx && y==fy)
		  ||(direction == Snake.UP && x==fx && y-Node.H==fy)
		  ||(direction == Snake.DOWN && x==fx && y+Node.H==fy)
		  ||(direction == Snake.LEFT && x-Node.W==fx && y==fy))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

class Node
{
	public int x,y;
	public static int W=20,H=20;
	Node(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
