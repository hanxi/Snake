package snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class SnakeFrame extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JMenuItem NewGameItem;
	private JMenuItem PauseGameItem;
	private JMenuItem ExitGameItem;
	private JMenu jMenu0;
	private JMenuItem SlowItem;
	private JMenuItem MidItem;
	private JMenuItem FastItem;
	private JMenu jMenu1;
	private JMenuBar jMenuBar0;

	private Snake snake;
	private SnakePanel snakePanel;
	public static int SLOW=200;
	public static int MID=150;
	public static int FAST=100;
	
	Thread control;
	
	public SnakeFrame()
	{
		initComponents();
	}

	private void initComponents() {
		setTitle("贪吃蛇");
		setFocusTraversalPolicyProvider(true);
		addKeyListener(new KeyAdapter() {
	
			public void keyPressed(KeyEvent event) {
				keyKeyPressed(event);
			}
		});
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.out.println("退出游戏！");
				control=null;
				System.exit(0);
			}
		});
		
		jMenuBar0 = new JMenuBar();
		jMenu1 = new JMenu();
		FastItem = new JMenuItem();
		jMenu0 = new JMenu();
		MidItem = new JMenuItem();
		SlowItem = new JMenuItem();
		PauseGameItem = new JMenuItem();
		NewGameItem = new JMenuItem();
		ExitGameItem = new JMenuItem("退出");
		
		setJMenuBar(jMenuBar0);
		setBounds(100,100,410, 310);
		setResizable(false);
		
		jMenuBar0.add(jMenu0);
		jMenuBar0.add(jMenu1);
		jMenu1.setText("等级");
		jMenu1.add(SlowItem);
		jMenu1.add(MidItem);
		jMenu1.add(FastItem);
		FastItem.setText("高级");	
		jMenu0.setText("游戏");
		jMenu0.setFocusable(false);
		jMenu0.add(NewGameItem);
		jMenu0.add(PauseGameItem);
		jMenu0.add(ExitGameItem);
		MidItem.setText("中级");
		SlowItem.setText("初级");
		PauseGameItem.setText("暂停");
		NewGameItem.setText("新游戏");
		
		FastItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				snake.SPEED = SnakeFrame.FAST;
			}
		});
		MidItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				snake.SPEED = SnakeFrame.MID;
			}
		});
		SlowItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				snake.SPEED = SnakeFrame.SLOW;
			}
		});
		ExitGameItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("退出游戏！");
				control=null;
				System.exit(0);
			}
		});
		PauseGameItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				snake.pause();
			}
		});
		NewGameItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				int speed = snake.SPEED;
				snake=new Snake();
				snakePanel.setSnake(snake);
				snake.SPEED = speed;
				control = new Thread(snakePanel);
				control.start();
				snake.pause();
			}
		});
		snake = new Snake();
		snakePanel = new SnakePanel(snake);
		control = new Thread(snakePanel);
		control.start();
		add(snakePanel);
		validate();
	}

	private void keyKeyPressed(KeyEvent event) {
		switch (event.getKeyCode())
		{
		case KeyEvent.VK_UP:
			snake.changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_DOWN:
			snake.changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			snake.changeDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			snake.changeDirection(Snake.RIGHT);
			break;
		case KeyEvent.VK_SPACE:
			snake.pause();
			break;
		}
	}
}
