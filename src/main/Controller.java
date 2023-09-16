package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import engine.Game;
import engine.Player;
import entity.Entity;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.GameActionException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.world.Champion;
import model.world.Condition;
import model.world.Cover;
import model.world.Damageable;
import model.world.Direction;

public class Controller implements ActionListener, KeyListener, MouseListener {
	public int n;
	Sound sound = new Sound();
	JFrame x = new JFrame();
	String first;
	String second;
	GameStarterPanel start;
	Game model;
	TheGame view2;
	GameViewer view;
	public boolean upPressed, downPressed, leftPressed, rightPressed, attackpressed, gameEnded, cast1, cast2, cast3,
			leader;
	ArrayList<JButton> buttons;
	JPanel team1;
	JPanel team2;
	static String[] champs = { "ca.png", "deadpool.png", "dr strange.png", "electro.png", "gr.png", "hela.png",
			"hulk.png", "iceman.png", "ironman.png", "loki.png", "qs.png", "sm.png", "thor.png", "venom.png",
			"yj.png" };
	static String[] champsi = { "cai.png", "deadpooli.png", "dr strangei.png", "electroi.png", "gri.png", "helai.png",
			"hulki.png", "icemani.png", "ironmani.png", "lokii.png", "qsi.png", "spidermani.png", "thori.png",
			"venomi.png", "yji.png" };
	Ability a;
	Player winner;
	ArrayList<Champion> turnchamps;
	ArrayList<JLabel> labels;

	public boolean isRightPressed() {
		return rightPressed;
	}

	public Controller() {
		playMusic(27);
		team1 = new JPanel();
		team2 = new JPanel();
		team1.setPreferredSize(new Dimension(730, 300));
		team2.setPreferredSize(new Dimension(730, 300));
//		team1.setBackground(Color.black);
//		team2.setBackground(Color.black);
		start = new GameStarterPanel();
		start.getEnterNamep1().addActionListener(this);
		start.getEnterNamep2().addActionListener(this);

		x.add(start);
		x.setUndecorated(true);
		x.pack();
		x.setExtendedState(x.MAXIMIZED_BOTH);
		x.setVisible(true);
		x.setResizable(false);

	}

	public void initialize() throws IOException {
		x.dispose();
		view = new GameViewer();
		model = new Game(new Player(first), new Player(second));
		model.onRun();

		JLabel a = new JLabel();
		a.setText(first + "'s team:");
		a.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
		a.setForeground(Color.white);

		JLabel b = new JLabel();
		b.setText(second + "'s team:");
		b.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
		b.setForeground(Color.white);

		a.setBounds(170, 130, 730, 40);
		b.setBounds(170, 430, 730, 40);
		view.getDesc().add(a);
		view.getDesc().add(b);

		team1.setBounds(0, 200, 530, 200);
		team2.setBounds(0, 500, 530, 200);
		team1.setOpaque(false);
		team2.setOpaque(false);
		team1.setLayout(new GridLayout(1, 3));
		team2.setLayout(new GridLayout(1, 3));

		view.getDesc().add(team1);
		view.getDesc().add(team2);

		buttons = new ArrayList<JButton>();

		for (int i = 0; i < model.getAvailableChampions().size(); i++) {
			JButton x = new JButton();
			x.setOpaque(false);
			x.setContentAreaFilled(false);
			x.setFocusPainted(false);
			x.setBorderPainted(false);
			// x.setBackground(new Color(000));
			// x.setText(model.getAvailableChampions().get(i).getName());
			// x.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
			x.setForeground(Color.white);
			x.setHorizontalTextPosition(JButton.CENTER);
			x.setVerticalTextPosition(JButton.TOP);
			buttons.add(x);

			ImageIcon icon = new ImageIcon(champs[i]);
			Image newimg = icon.getImage().getScaledInstance(200, 250, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);
			x.setIcon(icon);
			x.addActionListener(this);
			x.addMouseListener(this);
			view.choose.add(x);
		}
		view.revalidate();
		view.repaint();
	}

	public static void main(String[] args) throws IOException {
		new Controller();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("E1")) {
			if (start.getP1name().getText().equals("")) {
				JOptionPane.showMessageDialog(start, "Please enter name of Player one ", "Error",
						JOptionPane.ERROR_MESSAGE, start.getError());
			} else {
				first = start.getP1name().getText();
				start.getEnterNamep1().setEnabled(false);
				if (!start.getEnterNamep1().isEnabled() && !start.getEnterNamep2().isEnabled()) {
					start.setVisible(false);
					// view.choose.setVisible(true);
					if (first != null && second != null)
						try {
							initialize();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			}

		} else if (e.getActionCommand().equals("E2")) {
			if (start.getP2name().getText().equals("")) {
				JOptionPane.showMessageDialog(start, "Please enter name of Player two ", "Error",
						JOptionPane.ERROR_MESSAGE, start.getError());
			} else {
				second = start.getP2name().getText();
				start.getEnterNamep2().setEnabled(false);
				if (!start.getEnterNamep1().isEnabled() && !start.getEnterNamep2().isEnabled()) {
					start.setVisible(false);
					// view.choose.setVisible(true);
					if (first != null && second != null)
						try {
							initialize();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			}

		} else {
			JButton b = (JButton) e.getSource();
			int r = buttons.indexOf(b);
			makeSound(r);
			if (model.getFirstPlayer().getTeam().size() < 3)
				model.getFirstPlayer().getTeam().add(model.getAvailableChampions().get(r));
			else
				model.getSecondPlayer().getTeam().add(model.getAvailableChampions().get(r));
			b.setEnabled(false);
			JLabel x = new JLabel();
			ImageIcon icon = new ImageIcon(champs[r]);
			Image newimg = icon.getImage().getScaledInstance(150, 200, java.awt.Image.SCALE_SMOOTH); // smooth way
			icon = new ImageIcon(newimg);
			x.setIcon(icon);
			updateChoices(x);
			if (model.getFirstPlayer().getTeam().size() == 3 && model.getSecondPlayer().getTeam().size() == 3) {
				view.fl1.setVisible(true);
				view.fl2.setVisible(true);
				view.fl3.setVisible(true);
				view.fl4.setVisible(true);
				view.fl5.setVisible(true);
				view.fl6.setVisible(true);
				view.getDesc().setVisible(true);
				view.mp.setVisible(false);
				chooseLeader();
				view.choose.setVisible(false);
				view.getDesc().setVisible(false);
				view.dispose();
				model.placeChampions();
				ArrayList<Damageable> damageables = new ArrayList<Damageable>();
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (model.getBoard()[i][j] != null)
							damageables.add((Damageable) model.getBoard()[i][j]);
					}
				}
				try {
					view2 = new TheGame(this, damageables);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				model.prepareChampionTurns();
				try {
					this.updateCurrentChampionInfo(model.getCurrentChampion());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setChampionturn(model.getCurrentChampion());

			}
		}
	}

	private void setChampionturn(Champion c) {
		c.animation.keyH = this;

	}

	public void stopMusic() {
		sound.stop();
	}

	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}

	public void updateChoices(JLabel b) {

		if (model.getFirstPlayer().getTeam().size() <= 3 && model.getSecondPlayer().getTeam().size() == 0) {
			team1.add(b);

		} else {
			team2.add(b);

		}

	}

	public void chooseLeader() {
		ArrayList<Champion> t1 = model.getFirstPlayer().getTeam();
		String[] options = { t1.get(0).getName(), t1.get(1).getName(), t1.get(2).getName() };
		int i = JOptionPane.showOptionDialog(null, model.getFirstPlayer().getName() + " choose your leader",
				"YOUR LEADER IS ?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, 0);
		model.getFirstPlayer().setLeader(t1.get(i));
		ArrayList<Champion> t2 = model.getSecondPlayer().getTeam();
		String[] optionss = { t2.get(0).getName(), t2.get(1).getName(), t2.get(2).getName() };
		int j = JOptionPane.showOptionDialog(null, model.getSecondPlayer().getName() + " choose your leader",
				"YOUR LEADER IS ?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionss, 0);
		model.getSecondPlayer().setLeader(t2.get(j));

		view.getDesc().setVisible(false);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (view2 != null) {
			try {
				if (view2.board.choosingDirection != true && model.getCurrentChampion().animation.stop <= 0
						&& view2.board.choosingTarget != true && view2.board.getInfo != true) {
					if (code == KeyEvent.VK_UP && downPressed != true && leftPressed != true && rightPressed != true) {

						model.move(Direction.UP);
						this.updateCurrentChampionInfo(model.getCurrentChampion());
						model.getCurrentChampion().animation.stop = 46;
						upPressed = true;

					} else if (code == KeyEvent.VK_DOWN && model.getCurrentChampion().animation.stop <= 0
							&& upPressed != true && leftPressed != true && rightPressed != true) {

						model.move(Direction.DOWN);
						this.updateCurrentChampionInfo(model.getCurrentChampion());
						model.getCurrentChampion().animation.stop = 46;
						downPressed = true;

					} else if (code == KeyEvent.VK_RIGHT && model.getCurrentChampion().animation.stop <= 0
							&& downPressed != true && leftPressed != true && upPressed != true) {

						model.move(Direction.RIGHT);
						this.updateCurrentChampionInfo(model.getCurrentChampion());
						model.getCurrentChampion().animation.stop = 38;
						rightPressed = true;

					} else if (code == KeyEvent.VK_LEFT && model.getCurrentChampion().animation.stop <= 0
							&& downPressed != true && upPressed != true && rightPressed != true) {

						model.move(Direction.LEFT);
						this.updateCurrentChampionInfo(model.getCurrentChampion());
						model.getCurrentChampion().animation.stop = 38;
						leftPressed = true;

					} else if (code == KeyEvent.VK_W && downPressed != true && upPressed != true && rightPressed != true
							&& leftPressed != true && attackpressed != true) {

						model.attack(Direction.UP);
						this.updateCurrentChampionInfo(model.getCurrentChampion());
						model.getCurrentChampion().animation.stop = 35;
						attackpressed = true;
						if (model.checkGameOver() != null) {
							gameEnded = true;
							winner = model.checkGameOver();
						}

					} else if (code == KeyEvent.VK_S && downPressed != true && upPressed != true && rightPressed != true
							&& leftPressed != true && attackpressed != true) {

						model.attack(Direction.DOWN);
						this.updateCurrentChampionInfo(model.getCurrentChampion());
						model.getCurrentChampion().animation.stop = 35;
						attackpressed = true;
						if (model.checkGameOver() != null) {
							gameEnded = true;
							winner = model.checkGameOver();
						}

					} else if (code == KeyEvent.VK_D && downPressed != true && upPressed != true && rightPressed != true
							&& leftPressed != true && attackpressed != true) {

						model.attack(Direction.RIGHT);
						this.updateCurrentChampionInfo(model.getCurrentChampion());
						model.getCurrentChampion().animation.stop = 35;
						attackpressed = true;
						if (model.checkGameOver() != null) {
							gameEnded = true;
							winner = model.checkGameOver();
						}

					} else if (code == KeyEvent.VK_A && downPressed != true && upPressed != true && rightPressed != true
							&& leftPressed != true && attackpressed != true) {

						model.attack(Direction.LEFT);
						this.updateCurrentChampionInfo(model.getCurrentChampion());
						model.getCurrentChampion().animation.stop = 35;
						attackpressed = true;
						if (model.checkGameOver() != null) {
							gameEnded = true;
							winner = model.checkGameOver();
						}

					} else if (code == KeyEvent.VK_ENTER & downPressed != true && upPressed != true
							&& rightPressed != true && leftPressed != true && attackpressed != true) {
						model.getCurrentChampion().animation.keyH = null;
						model.endTurn();

						for (int i = 0; i < view2.board.visuals.size();) {
							if (view2.board.visuals.get(i).direction.equals("dead"))
								view2.board.visuals.remove(i);
							else
								i++;
						}
						model.getCurrentChampion().animation.keyH = this;
						this.updateCurrentChampionInfo(model.getCurrentChampion());
					} else if (code == KeyEvent.VK_X && downPressed != true && upPressed != true && rightPressed != true
							&& leftPressed != true && attackpressed != true) {

						model.useLeaderAbility();
						this.updateCurrentChampionInfo(model.getCurrentChampion());
						model.getCurrentChampion().animation.animateLeader();
						model.getCurrentChampion().animation.leaderAbilitySound();
						if (model.checkGameOver() != null) {
							gameEnded = true;
							winner = model.checkGameOver();
						}

					} else if ((code == KeyEvent.VK_1 || code == KeyEvent.VK_2 || code == KeyEvent.VK_3)
							&& downPressed != true && upPressed != true && rightPressed != true && leftPressed != true
							&& attackpressed != true) {
						if (code == KeyEvent.VK_1) {
							a = model.getCurrentChampion().getAbilities().get(0);
							n = 0;
						}
						if (code == KeyEvent.VK_2) {
							a = model.getCurrentChampion().getAbilities().get(1);
							n = 1;
						}
						if (code == KeyEvent.VK_3) {
							a = model.getCurrentChampion().getAbilities().get(2);
							n = 2;
						}
						if (a.getCastArea() != AreaOfEffect.DIRECTIONAL
								&& a.getCastArea() != AreaOfEffect.SINGLETARGET) {
							model.castAbility(a);
							this.updateCurrentChampionInfo(model.getCurrentChampion());
							model.getCurrentChampion().animation.animateAbility(n);
							model.getCurrentChampion().animation.ability1Sound(a.getName());

							if (model.checkGameOver() != null) {
								gameEnded = true;
								winner = model.checkGameOver();
							}
						} else if (a.getCastArea() == AreaOfEffect.DIRECTIONAL) {
							view2.board.choosingDirection = true;
							view2.board.ui.showMessage("Select the direction");
						} else if (a.getCastArea() == AreaOfEffect.SINGLETARGET) {
							view2.board.choosingTarget = true;
							view2.board.ui.showMessage("Select the target by toggling the cursor");
						}

					} else if ((code == KeyEvent.VK_G || code == KeyEvent.VK_2 || code == KeyEvent.VK_3)
							&& downPressed != true && upPressed != true && rightPressed != true && leftPressed != true
							&& attackpressed != true)
						view2.board.getInfo = true;
				}

				else if (view2.board.choosingDirection == true) {
					if (code == KeyEvent.VK_UP) {
						model.castAbility(a, Direction.UP);
						this.updateCurrentChampionInfo(model.getCurrentChampion());
						model.getCurrentChampion().animation.ability1Sound(a.getName());
						model.getCurrentChampion().animation.stop = 35;

						model.getCurrentChampion().animation.animateAbility(n);
						view2.board.choosingDirection = false;
						if (model.checkGameOver() != null) {
							gameEnded = true;
							winner = model.checkGameOver();
						}
					}
					if (code == KeyEvent.VK_DOWN) {
						model.castAbility(a, Direction.DOWN);
						this.updateCurrentChampionInfo(model.getCurrentChampion());
						model.getCurrentChampion().animation.ability1Sound(a.getName());
						model.getCurrentChampion().animation.stop = 35;
						model.getCurrentChampion().animation.animateAbility(n);
						view2.board.choosingDirection = false;
						if (model.checkGameOver() != null) {
							gameEnded = true;
							winner = model.checkGameOver();
						}
					}
					if (code == KeyEvent.VK_RIGHT) {
						model.castAbility(a, Direction.RIGHT);
						this.updateCurrentChampionInfo(model.getCurrentChampion());
						model.getCurrentChampion().animation.ability1Sound(a.getName());
						model.getCurrentChampion().animation.stop = 35;
						model.getCurrentChampion().animation.animateAbility(n);
						view2.board.choosingDirection = false;
						if (model.checkGameOver() != null) {
							gameEnded = true;
							winner = model.checkGameOver();
						}
					}
					if (code == KeyEvent.VK_LEFT) {
						model.castAbility(a, Direction.LEFT);
						this.updateCurrentChampionInfo(model.getCurrentChampion());
						model.getCurrentChampion().animation.ability1Sound(a.getName());
						model.getCurrentChampion().animation.animateAbility(n);
						view2.board.choosingDirection = false;
						if (model.checkGameOver() != null) {
							gameEnded = true;
							winner = model.checkGameOver();
						}
					}
				} else if (view2.board.choosingTarget == true) {
					if (code == KeyEvent.VK_UP) {
						if (view2.board.ui.slotRow > 0)
							view2.board.ui.slotRow--;
					}
					if (code == KeyEvent.VK_DOWN) {
						if (view2.board.ui.slotRow < 4)
							view2.board.ui.slotRow++;
					}
					if (code == KeyEvent.VK_RIGHT) {
						if (view2.board.ui.slotCol < 4)
							view2.board.ui.slotCol++;
					}
					if (code == KeyEvent.VK_LEFT) {
						if (view2.board.ui.slotCol > 0)
							view2.board.ui.slotCol--;
					}
					if (code == KeyEvent.VK_ENTER) {
						model.castAbility(a, 4 - view2.board.ui.slotRow, view2.board.ui.slotCol);
						this.updateCurrentChampionInfo(model.getCurrentChampion());
						model.getCurrentChampion().animation.ability1Sound(a.getName());

						model.getCurrentChampion().animation.animateAbility(n);
						view2.board.choosingTarget = false;
						if (model.checkGameOver() != null) {
							gameEnded = true;
							winner = model.checkGameOver();
						}
					}
					if (code == KeyEvent.VK_ESCAPE) {
						view2.board.choosingTarget = false;
					}
				} else if (view2.board.getInfo == true) {
					if (code == KeyEvent.VK_UP) {
						if (view2.board.ui.slotRow > 0)
							view2.board.ui.slotRow--;
					}
					if (code == KeyEvent.VK_DOWN) {
						if (view2.board.ui.slotRow < 4)
							view2.board.ui.slotRow++;
					}
					if (code == KeyEvent.VK_RIGHT) {
						if (view2.board.ui.slotCol < 4)
							view2.board.ui.slotCol++;
					}
					if (code == KeyEvent.VK_LEFT) {
						if (view2.board.ui.slotCol > 0)
							view2.board.ui.slotCol--;
					}
					if (code == KeyEvent.VK_ENTER)
						if (model.getBoard()[4 - view2.board.ui.slotRow][view2.board.ui.slotCol] instanceof Champion) {
							this.updateCurrentChampionInfo(
									(Champion) model.getBoard()[4 - view2.board.ui.slotRow][view2.board.ui.slotCol]);
							((Champion) model.getBoard()[4
									- view2.board.ui.slotRow][view2.board.ui.slotCol]).animation.attacked = true;
						} else if (model.getBoard()[4
								- view2.board.ui.slotRow][view2.board.ui.slotCol] instanceof Cover)
							((Cover) model.getBoard()[4
									- view2.board.ui.slotRow][view2.board.ui.slotCol]).animation.attacked = true;

					if (code == KeyEvent.VK_ESCAPE) {
						view2.board.getInfo = false;
						this.updateCurrentChampionInfo(model.getCurrentChampion());
					}
				}
			} catch (NotEnoughResourcesException nere) {
				view2.board.ui.showMessage(nere.getLocalizedMessage());
				view2.board.choosingDirection = false;
				view2.board.choosingTarget = false;
			} catch (CloneNotSupportedException cnse) {
				view2.board.ui.showMessage(cnse.getLocalizedMessage());

			} catch (AbilityUseException aue) {
				view2.board.ui.showMessage(aue.getLocalizedMessage());
				view2.board.choosingTarget = false;
				view2.board.choosingDirection = false;
			} catch (LeaderNotCurrentException lnce) {
				view2.board.ui.showMessage(lnce.getLocalizedMessage());
			} catch (LeaderAbilityAlreadyUsedException laaue) {
				view2.board.ui.showMessage(laaue.getLocalizedMessage());
			} catch (UnallowedMovementException gae) {
				view2.board.ui.showMessage(gae.getLocalizedMessage());
			} catch (InvalidTargetException ite) {
				view2.board.ui.showMessage(ite.getLocalizedMessage());
				view2.board.choosingTarget = false;
				view2.board.choosingDirection = false;
			} catch (ChampionDisarmedException cde) {
				try {
					model.castAbility(model.getCurrentChampion().getAbilities().get(3));
					model.getCurrentChampion().animation.stop = 40;
					attackpressed = true;
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				view2.board.ui.showMessage(cde.getLocalizedMessage());

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (model.getFirstPlayer().getTeam().size() == 3 && model.getSecondPlayer().getTeam().size() == 3) {
			JLabel b = (JLabel) e.getSource();
			int k = labels.indexOf(b);
			int a = model.getAvailableChampions().indexOf(turnchamps.get(k));
			view2.Ginfo.turnsdesc = new JLabel("" + model.getAvailableChampions().get(a));
			view2.Ginfo.turnsdesc.setBounds(0, 650, view2.board.screenWidth, 400);
			view2.Ginfo.add(view2.Ginfo.turnsdesc);
			view2.Ginfo.turnsdesc.setVisible(true);

		} else {
			JButton b = (JButton) e.getSource();
			int r = buttons.indexOf(b);
			view.getMp().getA().setText("" + model.getAvailableChampions().get(r).toStringl());
			view.getMp().setVisible(true);
			view.getMp().getA().setVisible(true);
		}
	}

	public void mouseExited(MouseEvent e) {
		view.getMp().setVisible(false);
		if (view2 != null)
			view2.Ginfo.remove(view2.Ginfo.turnsdesc);
	}

	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}

	public void updateCurrentChampionInfo(Champion c) throws IOException {
		view2.Ginfo.jp.removeAll();
		view2.Ginfo.turns.removeAll();

		ArrayList<Champion> t = new ArrayList<Champion>();
		turnchamps = new ArrayList<Champion>();
		labels = new ArrayList<JLabel>();

		JLabel current = new JLabel();
		current.setText("Current Turn: ");
		current.setForeground(Color.white);
		view2.Ginfo.turns.add(current);
		int z = 0;
		while (!model.getTurnOrder().isEmpty()) {
			if (z == 1) {
				JLabel next = new JLabel();
				next.setText("Next Turn: ");
				next.setForeground(Color.white);
				view2.Ginfo.turns.add(next);
			}if(!model.hasEffect((Champion) model.getTurnOrder().peekMin(), "Stun"))
				{int a = model.getAvailableChampions().indexOf((Champion) model.getTurnOrder().peekMin());
				ImageIcon image = new ImageIcon(champsi[a]);
				Image newimg = image.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
				image = new ImageIcon(newimg);
				JLabel label = new JLabel();
				label.setOpaque(false);
				label.setIcon(image);
				view2.Ginfo.turns.add(label);
				labels.add(label);}
				turnchamps.add((Champion) model.getTurnOrder().peekMin());
				t.add((Champion) model.getTurnOrder().remove());
				z++;
			
		}

		view2.Ginfo.turns.setBounds(0, 50, view2.board.screenWidth, 100);
		view2.Ginfo.turns.setBackground(Color.black);
		view2.Ginfo.add(view2.Ginfo.turns);

		for (int i = 0; i < t.size(); i++)
			model.getTurnOrder().insert(t.get(i));

		view2.Ginfo.l = new JLabel(c.toString2());
		view2.Ginfo.l.setVerticalTextPosition(JLabel.TOP);
		view2.Ginfo.m = new JLabel(c.toString3());
		view2.Ginfo.m.setVerticalTextPosition(JLabel.TOP);

		if (model.getFirstPlayer().getTeam().contains(model.getCurrentChampion())) {
			view2.Ginfo.pname = new JLabel(first + "'s turn");
			if (model.isFirstLeaderAbilityUsed())
				view2.Ginfo.leaderability = new JLabel("First leader ability got used");
			else
				view2.Ginfo.leaderability = new JLabel("First leader ability is still available");
		} else {
			view2.Ginfo.pname = new JLabel(second + "'s turn");
			if (model.isSecondLeaderAbilityUsed())
				view2.Ginfo.leaderability = new JLabel("Second leader ability got used");
			else
				view2.Ginfo.leaderability = new JLabel("Second leader ability is still available");
		}

		view2.Ginfo.jp.setBounds(0, 0, view2.board.screenWidth, view2.board.screenHeight);
		view2.Ginfo.jp.setVisible(true);

		BufferedImage myPicture = ImageIO.read(new File("gp.png"));
		ImageIcon xx = new ImageIcon(myPicture);
		Image newimg2 = xx.getImage().getScaledInstance(750, 850, java.awt.Image.SCALE_SMOOTH);
		xx = new ImageIcon(newimg2);
		JLabel picLabel = new JLabel(xx);
		picLabel.setBounds(10, 10, 750, 850);
		view2.Ginfo.jp.add(picLabel, Integer.valueOf(0));

		int a = model.getAvailableChampions().indexOf(c);
		ImageIcon image = new ImageIcon(champs[a]);
		Image newimg = image.getImage().getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH);
		image = new ImageIcon(newimg);
		JLabel label = new JLabel();
		label.setOpaque(false);
		label.setIcon(image);
		label.setBounds(80, 230, 200, 150);
		view2.Ginfo.jp.add(label, Integer.valueOf(1));
		view2.Ginfo.leaderability.setFont(new Font(Font.SERIF, Font.BOLD, 15));
		view2.Ginfo.leaderability.setBounds(100, 490, 250, 25);//
		view2.Ginfo.pname.setBounds(100, 150, 600, 25);
		view2.Ginfo.pname.setForeground(new Color(14, 200, 0));
		view2.Ginfo.pname.setFont(new Font(Font.SERIF, Font.BOLD, 30));
		view2.Ginfo.l.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
		view2.Ginfo.m.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
		view2.Ginfo.l.setBounds(100, 525, 200, 300);
		view2.Ginfo.m.setBounds(400, 150, 300, 700);

		view2.Ginfo.mana.setValue((int) (100 * (double) c.getMana() / (double) c.getMaxMana()));
		view2.Ginfo.mana.setStringPainted(true);
		view2.Ginfo.mana.setString(c.getMana() + " mana");
		view2.Ginfo.mana.setBounds(90, 420, 200, 20);
		view2.Ginfo.actionPoints
				.setValue((int) (100 * (double) c.getCurrentActionPoints() / (double) c.getMaxActionPointsPerTurn()));
		view2.Ginfo.actionPoints.setStringPainted(true);
		view2.Ginfo.actionPoints
				.setString(c.getCurrentActionPoints() + "/" + c.getMaxActionPointsPerTurn() + " points");
		view2.Ginfo.actionPoints.setBounds(90, 450, 200, 20);

		view2.Ginfo.l.setVerticalAlignment(view2.Ginfo.l.TOP);
		view2.Ginfo.m.setVerticalAlignment(view2.Ginfo.m.TOP);

		view2.Ginfo.jp.add(view2.Ginfo.l, Integer.valueOf(1));
		view2.Ginfo.jp.add(view2.Ginfo.pname, Integer.valueOf(1));
		view2.Ginfo.jp.add(view2.Ginfo.leaderability, Integer.valueOf(1));
		view2.Ginfo.jp.add(view2.Ginfo.m, Integer.valueOf(1));
		view2.Ginfo.jp.add(view2.Ginfo.mana, Integer.valueOf(1));
		view2.Ginfo.jp.add(view2.Ginfo.actionPoints, Integer.valueOf(1));

		view2.Ginfo.add(view2.Ginfo.jp);

		view2.Ginfo.l.setForeground(Color.white);
		view2.Ginfo.leaderability.setForeground(Color.white);
		view2.Ginfo.pname.setForeground(Color.white);
		view2.Ginfo.m.setForeground(Color.white);

		view2.repaint();
		view2.revalidate();

	}

	public void makeSound(int r) {
		if (r == 0)
			playSE(3);
		if (r == 1)
			playSE(5);
		if (r == 4)
			playSE(7);
		if (r == 6)
			playSE(8);
		if (r == 8)
			playSE(11);
		if (r == 11)
			playSE(14);
		if (r == 12)
			playSE(28);
		if (r == 13)
			playSE(19);
	}
}
