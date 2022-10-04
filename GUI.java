import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;

public class GUI {
    JFrame frame;
    JPanel panelMenu;
    JPanel loginMenu;
    JTextField location;
    JTextField username;
    JTextField password;
    JTextField loginUsername;
    JTextField loginPassword;
    Account newAccount;
    ArrayList<Account> accounts = new ArrayList<>();
    ArrayList<Account> tempAccounts = new ArrayList<>();
    File file = new File("accounts");
    
    public GUI() {
        checkForExisting();

        
        
        frame = new JFrame();
        panelMenu = new JPanel();
        loginMenu = new JPanel();
        frame.setSize(650, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Account Storage Application");
        frame.getContentPane().add(loginMenu);
        loginMenu.setLayout(null);
        JLabel login = new JLabel("Please login with username and password");
        login.setBounds(200, 10, 1000, 100);
        loginMenu.add(login);
        loginMenu.revalidate();
        JLabel usernamePrompt = new JLabel("Username:");
        usernamePrompt.setBounds(50, 80, 1000, 100);
        loginMenu.add(usernamePrompt);
        loginMenu.revalidate();
        loginUsername = new JTextField(10);
        loginUsername.setBounds(50, 150, 500, 30);
        loginMenu.add(loginUsername);
        loginMenu.revalidate();
        JLabel passwordPrompt = new JLabel("Password:");
        passwordPrompt.setBounds(50, 150, 1000, 100);
        loginMenu.add(passwordPrompt);
        loginMenu.revalidate();
        loginPassword = new JTextField(10);
        loginPassword.setBounds(50, 220, 500, 30);
        loginMenu.add(loginPassword);
        loginMenu.revalidate();
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(250, 300, 100, 30);
        loginMenu.add(loginButton);
        loginMenu.revalidate();
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loginUsername.getText().equals("username") && loginPassword.getText().equals("password")) {
                    frame.getContentPane().remove(loginMenu);
                    frame.getContentPane().add(panelMenu);
                    frame.setVisible(true);
                    frame.validate();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Wrong username or password!", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        frame.setVisible(true);

        panelMenu.setLayout(null);

        JLabel intro = new JLabel("Welcome! This program is designed to store all your accounts and their usernames and passwords.");
        intro.setBounds(45, 10, 1000, 100);
        panelMenu.add(intro);

        JButton add = new JButton("Add");
        add.setBounds(50, 150, 90, 90);
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add();
            }
        });
        panelMenu.add(add);

        JButton lookup = new JButton("Lookup");
        lookup.setBounds(160, 150, 90, 90);
        lookup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lookup();
            }
        });
        panelMenu.add(lookup);
        
        JButton delete = new JButton("Delete");
        delete.setBounds(270, 150, 90, 90);
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
        panelMenu.add(delete);

        JButton edit = new JButton("Edit");
        edit.setBounds(380, 150, 90, 90);
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edit();
            }
        });
        panelMenu.add(edit);

        JButton showAll = new JButton("Show All");
        showAll.setBounds(490, 150, 90, 90);
        showAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAll();
            }
        });
        panelMenu.add(showAll);

        JButton quit = new JButton("Quit");
        quit.setBounds(290, 300, 90, 90);
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeToFile();
                System.exit(0);
            }
        });
        panelMenu.add(quit);
    }

    public void checkForExisting() {
        if (file.exists() && file.length() != 0) {
            try {
                FileInputStream fis = new FileInputStream("accounts");
                ObjectInputStream ois = new ObjectInputStream(fis);
                accounts = (ArrayList) ois.readObject();
                ois.close();
                fis.close();
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
                return;
            }
            catch (ClassNotFoundException c) {
                System.out.println("Class not found");
                c.printStackTrace();
                return;
            }
        }
    }

    public void writeToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("accounts");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(accounts);
            oos.close();
            fos.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void add() {
        JPanel addPanel = new JPanel();
        addPanel.setLayout(null);
        frame.getContentPane().remove(panelMenu);
        frame.getContentPane().add(addPanel);
        frame.validate();
        
        JButton cancel = new JButton("Cancel");
        cancel.setBounds(250, 400, 100, 30);
        addPanel.add(cancel);
        addPanel.revalidate();

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.getContentPane().add(panelMenu);
                frame.validate();
            }
        });
        
        JLabel locationPrompt = new JLabel("Enter name of website or location of account to store:");
        locationPrompt.setBounds(50, 10, 1000, 100);
        addPanel.add(locationPrompt);
        addPanel.revalidate();

        location = new JTextField(20);
        location.setBounds(50, 80, 500, 30);
        addPanel.add(location);
        addPanel.revalidate();
        
        JLabel usernamePrompt = new JLabel("Enter username of the account:");
        usernamePrompt.setBounds(50, 80, 1000, 100);
        addPanel.add(usernamePrompt);
        usernamePrompt.setVisible(false);
        addPanel.revalidate();

        username = new JTextField(20);
        username.setBounds(50, 150, 500, 30);
        addPanel.add(username);
        username.setVisible(false);
        addPanel.revalidate();

        location.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usernamePrompt.setVisible(true);
                username.setVisible(true);
                addPanel.revalidate();
            }
        });

        JLabel passwordPrompt = new JLabel("Enter password of the account:");
        passwordPrompt.setBounds(50, 150, 1000, 100);
        addPanel.add(passwordPrompt);
        passwordPrompt.setVisible(false);
        addPanel.revalidate();

        password = new JTextField(20);
        password.setBounds(50, 220, 500, 30);
        addPanel.add(password);
        password.setVisible(false);
        addPanel.revalidate();

        username.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                passwordPrompt.setVisible(true);
                password.setVisible(true);
                addPanel.revalidate();
            }
        });

        password.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newAccount = new Account(username.getText(), password.getText(), location.getText());
                accounts.add(newAccount);

                JLabel account = new JLabel(newAccount.getInfo());
                account.setBounds(130, 190, 300, 300);
                addPanel.add(account);
                account.setVisible(true);
                addPanel.repaint();
                addPanel.revalidate();

                cancel.setVisible(false);
                addPanel.revalidate();

                JButton done = new JButton("Done");
                done.setBounds(400, 290, 100, 30);
                addPanel.add(done);
                addPanel.revalidate();

                done.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        frame.getContentPane().add(panelMenu);
                        frame.validate();
                    }
                });
                
            }
        });
    }

    public void lookup() {
        JPanel lookupPanel = new JPanel();
        lookupPanel.setLayout(null);
        frame.getContentPane().remove(panelMenu);
        frame.getContentPane().add(lookupPanel);
        frame.validate();

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(250, 400, 100, 30);
        lookupPanel.add(cancel);
        lookupPanel.revalidate();

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.getContentPane().add(panelMenu);
                frame.validate();
            }
        });

        JLabel lookupPrompt = new JLabel("Enter name of location of account to lookup:");
        lookupPrompt.setBounds(50, 10, 1000, 100);
        lookupPanel.add(lookupPrompt);
        lookupPanel.revalidate();

        JTextField lookup = new JTextField();
        lookup.setBounds(50, 80, 500, 30);
        lookupPanel.add(lookup);
        lookupPanel.revalidate();

        JPanel lookupResult = new JPanel();
        lookupResult.setLayout(new BoxLayout(lookupResult, BoxLayout.PAGE_AXIS));
        JScrollPane scrollPane = new JScrollPane(lookupResult, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        lookup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.getContentPane().add(scrollPane);
                frame.validate();
                boolean found = false;
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getLocation().equals(lookup.getText())) {
                        JLabel account = new JLabel(accounts.get(i).getInfo());
                        lookupResult.add(account);
                        lookupResult.revalidate();
                        found = true;
                    }
                }

                if (!found) {
                    JLabel notFound = new JLabel("Account not found!");
                    lookupResult.add(notFound);
                    notFound.setAlignmentX(Component.CENTER_ALIGNMENT);
                    lookupResult.revalidate();
                }
                
                JButton done = new JButton("Done");
                lookupResult.add(done);
                lookupResult.revalidate();

                JPanel sizePanel = new JPanel();
                sizePanel.add(done);
                lookupResult.add(sizePanel);

                done.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        frame.getContentPane().add(panelMenu);
                        frame.validate();
                    }
                });
            }
        });
    }

    public void showAll() {
        JPanel showAllPanel = new JPanel();
        showAllPanel.setLayout(new BoxLayout(showAllPanel, BoxLayout.PAGE_AXIS));
        JScrollPane scrollPane = new JScrollPane(showAllPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.getContentPane().remove(panelMenu);
        frame.getContentPane().add(scrollPane);
        frame.validate();

        for (int i = 0; i < accounts.size(); i++) {
            JLabel account = new JLabel(accounts.get(i).getInfo());
            showAllPanel.add(account);
            showAllPanel.revalidate();
        }

        JButton done = new JButton("Done");
        showAllPanel.add(done);
        showAllPanel.revalidate();

        JPanel sizePanel = new JPanel();
        sizePanel.add(done);
        showAllPanel.add(sizePanel);

        done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.getContentPane().add(panelMenu);
                frame.validate();
            }
        });        
    }

    public void delete() {
        JPanel deletePanel = new JPanel();
        deletePanel.setLayout(null);
        frame.getContentPane().remove(panelMenu);
        frame.getContentPane().add(deletePanel);
        frame.validate();

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(250, 400, 100, 30);
        deletePanel.add(cancel);
        deletePanel.revalidate();

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.getContentPane().add(panelMenu);
                frame.validate();
            }
        });

        JLabel deletePrompt = new JLabel("Enter name of location of account to delete:");
        deletePrompt.setBounds(50, 10, 1000, 100);
        deletePanel.add(deletePrompt);
        deletePanel.revalidate();

        JTextField delete = new JTextField();
        delete.setBounds(50, 80, 500, 30);
        deletePanel.add(delete);
        deletePanel.revalidate();

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getLocation().equals(delete.getText())) {
                        tempAccounts.add(accounts.get(i));
                    }
                }

                JButton done = new JButton("Done");
                done.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        frame.getContentPane().add(panelMenu);
                        frame.validate();
                    }
                });
        
                if (tempAccounts.size() == 0) {
                    JPanel noAccounts = new JPanel();
                    noAccounts.setLayout(null);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(noAccounts);
                    frame.validate();

                    JLabel noAccount = new JLabel("Account not found!");
                    noAccount.setBounds(250, 10, 1000, 100);
                    noAccounts.add(noAccount);
                    noAccount.revalidate();

                    done.setBounds(250, 80, 100, 30);
                    noAccounts.add(done);
                    noAccounts.revalidate();

                    tempAccounts.clear();
                }

                else if (tempAccounts.size() == 1) {
                    JPanel deletedAccount = new JPanel();
                    deletedAccount.setLayout(new BoxLayout(deletedAccount, BoxLayout.PAGE_AXIS));
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(deletedAccount);
                    frame.validate();

                    JLabel accountDelete = new JLabel("Account deleted: ");
                    deletedAccount.add(accountDelete);
                    deletedAccount.validate();
                    
                    JLabel account = new JLabel(tempAccounts.get(0).getInfo());
                    deletedAccount.add(account);
                    deletedAccount.validate();

                    deletedAccount.add(done);
                    deletedAccount.revalidate();

                    for (int i = 0; i < accounts.size(); i++) {
                        if (accounts.get(i).equals(tempAccounts.get(0))) {
                            accounts.remove(i);
                        }
                    }
                    tempAccounts.clear();
                }

                else {
                    JPanel multipleAccounts = new JPanel();
                    multipleAccounts.setLayout(new BoxLayout(multipleAccounts, BoxLayout.PAGE_AXIS));
                    JScrollPane scrollPane = new JScrollPane(multipleAccounts, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(scrollPane);
                    frame.validate();

                    JLabel multipleAccountsPrompt = new JLabel("There are multiple accounts associated with this location:");
                    multipleAccounts.add(multipleAccountsPrompt);
                    multipleAccountsPrompt.setAlignmentX(Component.LEFT_ALIGNMENT);
                    multipleAccounts.revalidate();

                    for (int i = 0; i < tempAccounts.size(); i++) {
                        JLabel accountNum = new JLabel(i + 1 + ".");
                        accountNum.setAlignmentX(Component.LEFT_ALIGNMENT);
                        multipleAccounts.add(accountNum);
                        multipleAccounts.revalidate();

                        JLabel account = new JLabel(tempAccounts.get(i).getInfo());
                        account.setAlignmentX(Component.LEFT_ALIGNMENT);
                        multipleAccounts.add(account);
                        multipleAccounts.revalidate();
                    }

                    JLabel numPrompt = new JLabel("Which account would you like to delete (Enter number):");
                    numPrompt.setAlignmentX(Component.LEFT_ALIGNMENT);
                    multipleAccounts.add(numPrompt);
                    multipleAccounts.revalidate();

                    JTextField numToDelete = new JTextField();
                    multipleAccounts.add(numToDelete);
                    numToDelete.setPreferredSize(new Dimension(100, 30));
                    numToDelete.setMaximumSize(numToDelete.getPreferredSize());
                    numToDelete.setAlignmentX(Component.LEFT_ALIGNMENT);
                    multipleAccounts.revalidate();
                    numToDelete.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JPanel accountDeleted = new JPanel();
                            accountDeleted.setLayout(new BoxLayout(accountDeleted, BoxLayout.PAGE_AXIS));
                            frame.getContentPane().removeAll();
                            frame.getContentPane().add(accountDeleted);
                            frame.validate();
                            
                            int choice = Integer.parseInt(numToDelete.getText()) - 1;

                            for (int i = 0; i < tempAccounts.size(); i++) {
                                if (choice == i) {
                                    for (int j = 0; j < accounts.size(); j++) {
                                        if (accounts.get(j).equals(tempAccounts.get(i))) {
                                            JLabel deleted = new JLabel("Account deleted:");
                                            deleted.setAlignmentX(Component.LEFT_ALIGNMENT);
                                            accountDeleted.add(deleted);
                                            accountDeleted.revalidate();

                                            JLabel deletedAccount = new JLabel(accounts.get(j).getInfo());
                                            deletedAccount.setAlignmentX(Component.LEFT_ALIGNMENT);
                                            accountDeleted.add(deletedAccount);
                                            accountDeleted.revalidate();

                                            JButton done = new JButton("Done");
                                            accountDeleted.add(done);
                                            accountDeleted.revalidate();
                                            done.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    frame.getContentPane().removeAll();
                                                    frame.repaint();
                                                    frame.getContentPane().add(panelMenu);
                                                    frame.validate();
                                                }
                                            });

                                            accounts.remove(j);

                                            break;
                                        }
                                    }
                                }
                            }
                            tempAccounts.clear();
                        }
                    });

                    JButton cancel = new JButton("Cancel");
                    multipleAccounts.add(cancel);
                    cancel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    cancel.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            tempAccounts.clear();
                            frame.getContentPane().removeAll();
                            frame.repaint();
                            frame.getContentPane().add(panelMenu);
                            frame.validate();
                        }
                    });
                    multipleAccounts.revalidate();
                }
            }
        });
    }

    public void edit() {
        JPanel editPanel = new JPanel();
        editPanel.setLayout(null);
        frame.getContentPane().remove(panelMenu);
        frame.getContentPane().add(editPanel);
        frame.validate();

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(250, 400, 100, 30);
        editPanel.add(cancel);
        editPanel.revalidate();

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.getContentPane().add(panelMenu);
                frame.validate();
            }
        });

        JLabel editPrompt = new JLabel("Enter name of location of account to edit:");
        editPrompt.setBounds(50, 10, 1000, 100);
        editPanel.add(editPrompt);
        editPanel.revalidate();

        JTextField edit = new JTextField();
        edit.setBounds(50, 80, 500, 30);
        editPanel.add(edit);
        editPanel.revalidate();

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getLocation().equals(edit.getText())) {
                        tempAccounts.add(accounts.get(i));
                    }
                }

                JButton done = new JButton("Done");
                done.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        frame.getContentPane().add(panelMenu);
                        frame.validate();
                    }
                });

                if (tempAccounts.size() == 0) {
                    JPanel noAccounts = new JPanel();
                    noAccounts.setLayout(null);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(noAccounts);
                    frame.validate();

                    JLabel noAccount = new JLabel("Account not found!");
                    noAccount.setBounds(250, 10, 1000, 100);
                    noAccounts.add(noAccount);
                    noAccount.revalidate();

                    done.setBounds(250, 80, 100, 30);
                    noAccounts.add(done);
                    noAccounts.revalidate();

                    tempAccounts.clear();
                }

                else if (tempAccounts.size() == 1) {
                    JPanel editedAccount = new JPanel();
                    editedAccount.setLayout(new BoxLayout(editedAccount, BoxLayout.PAGE_AXIS));
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(editedAccount);
                    frame.validate();

                    JLabel accountEdit = new JLabel(tempAccounts.get(0).getInfo());
                    editedAccount.add(accountEdit);
                    editedAccount.revalidate();

                    for (int i = 0; i < accounts.size(); i++) {
                        if (accounts.get(i).equals(tempAccounts.get(0))) {
                            Account editAccount = accounts.get(i);
                            
                            JLabel choosePrompt = new JLabel("Which would you like to edit?");
                            editedAccount.add(choosePrompt);
                            editedAccount.revalidate();

                            editedAccount.add(Box.createRigidArea(new Dimension(0, 10)));
                            editedAccount.revalidate();

                            JButton location = new JButton("Location");
                            editedAccount.add(location);
                            editedAccount.revalidate();

                            editedAccount.add(Box.createRigidArea(new Dimension(0, 10)));
                            editedAccount.revalidate();

                            JButton username = new JButton("Username");
                            editedAccount.add(username);
                            editedAccount.revalidate();

                            editedAccount.add(Box.createRigidArea(new Dimension(0, 10)));
                            editedAccount.revalidate();

                            JButton password = new JButton("Password");
                            editedAccount.add(password);
                            editedAccount.revalidate();

                            editedAccount.add(Box.createRigidArea(new Dimension(0, 200)));
                            editedAccount.revalidate();

                            JButton cancel = new JButton("Cancel");
                            editedAccount.add(cancel);
                            cancel.setAlignmentX(Component.LEFT_ALIGNMENT);
                            cancel.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    frame.getContentPane().removeAll();
                                    frame.repaint();
                                    frame.getContentPane().add(panelMenu);
                                    frame.validate();
                                }
                            });
                            editedAccount.revalidate();

                            location.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    JPanel editLocation = new JPanel();
                                    editLocation.setLayout(new BoxLayout(editLocation, BoxLayout.PAGE_AXIS));
                                    frame.getContentPane().removeAll();
                                    frame.getContentPane().add(editLocation);
                                    frame.validate();

                                    JLabel account = new JLabel(editAccount.getInfo());
                                    editLocation.add(account);
                                    editLocation.revalidate();

                                    JLabel locationPrompt = new JLabel("Enter new location:");
                                    editLocation.add(locationPrompt);
                                    editLocation.revalidate();

                                    JTextField locationInput = new JTextField();
                                    locationInput.setPreferredSize(new Dimension(200, 30));
                                    locationInput.setMaximumSize(locationInput.getPreferredSize());
                                    editLocation.add(locationInput);
                                    locationInput.setAlignmentX(Component.LEFT_ALIGNMENT);
                                    editLocation.revalidate();

                                    JButton cancel = new JButton("Cancel");
                                    editLocation.add(cancel);
                                    cancel.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            tempAccounts.clear();
                                            frame.getContentPane().removeAll();
                                            frame.repaint();
                                            frame.getContentPane().add(editedAccount);
                                            frame.validate();
                                        }
                                    });
                                    editLocation.revalidate();

                                    locationInput.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            for (int i = 0; i < accounts.size(); i++) {
                                                if (accounts.get(i).equals(editAccount)) {
                                                    accounts.get(i).setLocation(locationInput.getText());
                                                    JLabel newPrompt = new JLabel("New Account:");
                                                    editLocation.add(newPrompt);
                                                    JLabel newAccount = new JLabel(accounts.get(i).getInfo());
                                                    JButton done = new JButton("Done");
                                                    done.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            tempAccounts.clear();
                                                            frame.getContentPane().removeAll();
                                                            frame.repaint();
                                                            frame.getContentPane().add(panelMenu);
                                                            frame.validate();
                                                        }
                                                    });
                                                    cancel.setVisible(false);
                                                    locationInput.setVisible(false);
                                                    locationPrompt.setVisible(false);
                                                    editLocation.add(newAccount);
                                                    editLocation.add(done);
                                                    editLocation.revalidate();
                                                    break;
                                                }
                                            }   
                                        }
                                    });
                                }
                            });

                            username.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    JPanel editUsername = new JPanel();
                                    editUsername.setLayout(new BoxLayout(editUsername, BoxLayout.PAGE_AXIS));
                                    frame.getContentPane().removeAll();
                                    frame.getContentPane().add(editUsername);
                                    frame.validate();

                                    JLabel account = new JLabel(editAccount.getInfo());
                                    editUsername.add(account);
                                    editUsername.revalidate();

                                    JLabel usernamePrompt = new JLabel("Enter new username:");
                                    editUsername.add(usernamePrompt);
                                    editUsername.revalidate();

                                    JTextField usernameInput = new JTextField();
                                    usernameInput.setPreferredSize(new Dimension(200, 30));
                                    usernameInput.setMaximumSize(usernameInput.getPreferredSize());
                                    editUsername.add(usernameInput);
                                    usernameInput.setAlignmentX(Component.LEFT_ALIGNMENT);
                                    editUsername.revalidate();

                                    JButton cancel = new JButton("Cancel");
                                    editUsername.add(cancel);
                                    cancel.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            tempAccounts.clear();
                                            frame.getContentPane().removeAll();
                                            frame.repaint();
                                            frame.getContentPane().add(editedAccount);
                                            frame.validate();
                                        }
                                    });
                                    editUsername.revalidate();

                                    usernameInput.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            for (int i = 0; i < accounts.size(); i++) {
                                                if (accounts.get(i).equals(editAccount)) {
                                                    accounts.get(i).setUsername(usernameInput.getText());
                                                    JLabel newPrompt = new JLabel("New Account:");
                                                    editUsername.add(newPrompt);
                                                    JLabel newAccount = new JLabel(accounts.get(i).getInfo());
                                                    JButton done = new JButton("Done");
                                                    done.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            tempAccounts.clear();
                                                            frame.getContentPane().removeAll();
                                                            frame.repaint();
                                                            frame.getContentPane().add(panelMenu);
                                                            frame.validate();
                                                        }
                                                    });
                                                    cancel.setVisible(false);
                                                    usernameInput.setVisible(false);
                                                    usernamePrompt.setVisible(false);
                                                    editUsername.add(newAccount);
                                                    editUsername.add(done);
                                                    editUsername.revalidate();
                                                    break;
                                                }
                                            }   
                                        }
                                    });
                                }
                            });

                            password.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    JPanel editPassword = new JPanel();
                                    editPassword.setLayout(new BoxLayout(editPassword, BoxLayout.PAGE_AXIS));
                                    frame.getContentPane().removeAll();
                                    frame.getContentPane().add(editPassword);
                                    frame.validate();

                                    JLabel account = new JLabel(editAccount.getInfo());
                                    editPassword.add(account);
                                    editPassword.revalidate();

                                    JLabel passwordPrompt = new JLabel("Enter new password:");
                                    editPassword.add(passwordPrompt);
                                    editPassword.revalidate();

                                    JTextField passwordInput = new JTextField();
                                    passwordInput.setPreferredSize(new Dimension(200, 30));
                                    passwordInput.setMaximumSize(passwordInput.getPreferredSize());
                                    editPassword.add(passwordInput);
                                    passwordInput.setAlignmentX(Component.LEFT_ALIGNMENT);
                                    editPassword.revalidate();

                                    JButton cancel = new JButton("Cancel");
                                    editPassword.add(cancel);
                                    cancel.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            tempAccounts.clear();
                                            frame.getContentPane().removeAll();
                                            frame.repaint();
                                            frame.getContentPane().add(editedAccount);
                                            frame.validate();
                                        }
                                    });
                                    editPassword.revalidate();

                                    passwordInput.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            for (int i = 0; i < accounts.size(); i++) {
                                                if (accounts.get(i).equals(editAccount)) {
                                                    accounts.get(i).setPassword(passwordInput.getText());
                                                    JLabel newPrompt = new JLabel("New Account:");
                                                    editPassword.add(newPrompt);
                                                    JLabel newAccount = new JLabel(accounts.get(i).getInfo());
                                                    JButton done = new JButton("Done");
                                                    done.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            tempAccounts.clear();
                                                            frame.getContentPane().removeAll();
                                                            frame.repaint();
                                                            frame.getContentPane().add(panelMenu);
                                                            frame.validate();
                                                        }
                                                    });
                                                    cancel.setVisible(false);
                                                    passwordInput.setVisible(false);
                                                    passwordPrompt.setVisible(false);
                                                    editPassword.add(newAccount);
                                                    editPassword.add(done);
                                                    editPassword.revalidate();
                                                    break;
                                                }
                                            }   
                                        }
                                    });
                                }
                            });
                        }
                    }
                }

                else {
                    JPanel multipleAccounts = new JPanel();
                    multipleAccounts.setLayout(new BoxLayout(multipleAccounts, BoxLayout.PAGE_AXIS));
                    JScrollPane scrollPane = new JScrollPane(multipleAccounts, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(scrollPane);
                    frame.validate();

                    JLabel multipleAccountsPrompt = new JLabel("There are multiple accounts associated with this location:");
                    multipleAccounts.add(multipleAccountsPrompt);
                    multipleAccountsPrompt.setAlignmentX(Component.LEFT_ALIGNMENT);
                    multipleAccounts.revalidate();

                    for (int i = 0; i < tempAccounts.size(); i++) {
                        JLabel accountNum = new JLabel(i + 1 + ".");
                        accountNum.setAlignmentX(Component.LEFT_ALIGNMENT);
                        multipleAccounts.add(accountNum);
                        multipleAccounts.revalidate();

                        JLabel account = new JLabel(tempAccounts.get(i).getInfo());
                        account.setAlignmentX(Component.LEFT_ALIGNMENT);
                        multipleAccounts.add(account);
                        multipleAccounts.revalidate();
                    }

                    JLabel numPrompt = new JLabel("Which account would you like to edit (Enter number):");
                    numPrompt.setAlignmentX(Component.LEFT_ALIGNMENT);
                    multipleAccounts.add(numPrompt);
                    multipleAccounts.revalidate();

                    JTextField numToEdit = new JTextField();
                    multipleAccounts.add(numToEdit);
                    numToEdit.setPreferredSize(new Dimension(100, 30));
                    numToEdit.setMaximumSize(numToEdit.getPreferredSize());
                    numToEdit.setAlignmentX(Component.LEFT_ALIGNMENT);
                    multipleAccounts.revalidate();
                    numToEdit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JPanel editedAccount = new JPanel();
                            editedAccount.setLayout(new BoxLayout(editedAccount, BoxLayout.PAGE_AXIS));
                            frame.getContentPane().removeAll();
                            frame.getContentPane().add(editedAccount);
                            frame.validate();
                            
                            int choice = Integer.parseInt(numToEdit.getText()) - 1;

                            for (int i = 0; i < tempAccounts.size(); i++) {
                                if (i == choice) {
                                    for (int j = 0; j < accounts.size(); j++) {
                                        if (accounts.get(j).equals(tempAccounts.get(i))) {
                                            Account editAccount = accounts.get(j);

                                            JLabel account = new JLabel(editAccount.getInfo());
                                            editedAccount.add(account);
                                            editedAccount.revalidate();

                                            JLabel choosePrompt = new JLabel("Which would you like to edit?");
                                            editedAccount.add(choosePrompt);
                                            editedAccount.revalidate();

                                            editedAccount.add(Box.createRigidArea(new Dimension(0, 10)));
                                            editedAccount.revalidate();

                                            JButton location = new JButton("Location");
                                            editedAccount.add(location);
                                            editedAccount.revalidate();

                                            editedAccount.add(Box.createRigidArea(new Dimension(0, 10)));
                                            editedAccount.revalidate();

                                            JButton username = new JButton("Username");
                                            editedAccount.add(username);
                                            editedAccount.revalidate();

                                            editedAccount.add(Box.createRigidArea(new Dimension(0, 10)));
                                            editedAccount.revalidate();

                                            JButton password = new JButton("Password");
                                            editedAccount.add(password);
                                            editedAccount.revalidate();

                                            editedAccount.add(Box.createRigidArea(new Dimension(0, 200)));
                                            editedAccount.revalidate();

                                            JButton cancel = new JButton("Cancel");
                                            editedAccount.add(cancel);
                                            cancel.setAlignmentX(Component.LEFT_ALIGNMENT);
                                            cancel.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    frame.getContentPane().removeAll();
                                                    frame.repaint();
                                                    frame.getContentPane().add(panelMenu);
                                                    frame.validate();
                                                }
                                            });
                                            editedAccount.revalidate();

                                            location.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    JPanel editLocation = new JPanel();
                                                    editLocation.setLayout(new BoxLayout(editLocation, BoxLayout.PAGE_AXIS));
                                                    frame.getContentPane().removeAll();
                                                    frame.getContentPane().add(editLocation);
                                                    frame.validate();
                
                                                    JLabel account = new JLabel(editAccount.getInfo());
                                                    editLocation.add(account);
                                                    editLocation.revalidate();
                
                                                    JLabel locationPrompt = new JLabel("Enter new location:");
                                                    editLocation.add(locationPrompt);
                                                    editLocation.revalidate();
                
                                                    JTextField locationInput = new JTextField();
                                                    locationInput.setPreferredSize(new Dimension(200, 30));
                                                    locationInput.setMaximumSize(locationInput.getPreferredSize());
                                                    editLocation.add(locationInput);
                                                    locationInput.setAlignmentX(Component.LEFT_ALIGNMENT);
                                                    editLocation.revalidate();
                
                                                    JButton cancel = new JButton("Cancel");
                                                    editLocation.add(cancel);
                                                    cancel.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            tempAccounts.clear();
                                                            frame.getContentPane().removeAll();
                                                            frame.repaint();
                                                            frame.getContentPane().add(editedAccount);
                                                            frame.validate();
                                                        }
                                                    });
                                                    editLocation.revalidate();
                
                                                    locationInput.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            for (int i = 0; i < accounts.size(); i++) {
                                                                if (accounts.get(i).equals(editAccount)) {
                                                                    accounts.get(i).setLocation(locationInput.getText());
                                                                    JLabel newPrompt = new JLabel("New Account:");
                                                                    editLocation.add(newPrompt);
                                                                    JLabel newAccount = new JLabel(accounts.get(i).getInfo());
                                                                    JButton done = new JButton("Done");
                                                                    done.addActionListener(new ActionListener() {
                                                                        public void actionPerformed(ActionEvent e) {
                                                                            tempAccounts.clear();
                                                                            frame.getContentPane().removeAll();
                                                                            frame.repaint();
                                                                            frame.getContentPane().add(panelMenu);
                                                                            frame.validate();
                                                                        }
                                                                    });
                                                                    cancel.setVisible(false);
                                                                    locationInput.setVisible(false);
                                                                    locationPrompt.setVisible(false);
                                                                    editLocation.add(newAccount);
                                                                    editLocation.add(done);
                                                                    editLocation.revalidate();
                                                                    break;
                                                                }
                                                            }   
                                                        }
                                                    });
                                                }
                                            });

                                            username.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    JPanel editUsername = new JPanel();
                                                    editUsername.setLayout(new BoxLayout(editUsername, BoxLayout.PAGE_AXIS));
                                                    frame.getContentPane().removeAll();
                                                    frame.getContentPane().add(editUsername);
                                                    frame.validate();
                
                                                    JLabel account = new JLabel(editAccount.getInfo());
                                                    editUsername.add(account);
                                                    editUsername.revalidate();
                
                                                    JLabel usernamePrompt = new JLabel("Enter new username:");
                                                    editUsername.add(usernamePrompt);
                                                    editUsername.revalidate();
                
                                                    JTextField usernameInput = new JTextField();
                                                    usernameInput.setPreferredSize(new Dimension(200, 30));
                                                    usernameInput.setMaximumSize(usernameInput.getPreferredSize());
                                                    editUsername.add(usernameInput);
                                                    usernameInput.setAlignmentX(Component.LEFT_ALIGNMENT);
                                                    editUsername.revalidate();
                
                                                    JButton cancel = new JButton("Cancel");
                                                    editUsername.add(cancel);
                                                    cancel.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            tempAccounts.clear();
                                                            frame.getContentPane().removeAll();
                                                            frame.repaint();
                                                            frame.getContentPane().add(editedAccount);
                                                            frame.validate();
                                                        }
                                                    });
                                                    editUsername.revalidate();
                
                                                    usernameInput.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            for (int i = 0; i < accounts.size(); i++) {
                                                                if (accounts.get(i).equals(editAccount)) {
                                                                    accounts.get(i).setUsername(usernameInput.getText());
                                                                    JLabel newPrompt = new JLabel("New Account:");
                                                                    editUsername.add(newPrompt);
                                                                    JLabel newAccount = new JLabel(accounts.get(i).getInfo());
                                                                    JButton done = new JButton("Done");
                                                                    done.addActionListener(new ActionListener() {
                                                                        public void actionPerformed(ActionEvent e) {
                                                                            tempAccounts.clear();
                                                                            frame.getContentPane().removeAll();
                                                                            frame.repaint();
                                                                            frame.getContentPane().add(panelMenu);
                                                                            frame.validate();
                                                                        }
                                                                    });
                                                                    cancel.setVisible(false);
                                                                    usernameInput.setVisible(false);
                                                                    usernamePrompt.setVisible(false);
                                                                    editUsername.add(newAccount);
                                                                    editUsername.add(done);
                                                                    editUsername.revalidate();
                                                                    break;
                                                                }
                                                            }   
                                                        }
                                                    });
                                                }
                                            });

                                            password.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    JPanel editPassword = new JPanel();
                                                    editPassword.setLayout(new BoxLayout(editPassword, BoxLayout.PAGE_AXIS));
                                                    frame.getContentPane().removeAll();
                                                    frame.getContentPane().add(editPassword);
                                                    frame.validate();
                
                                                    JLabel account = new JLabel(editAccount.getInfo());
                                                    editPassword.add(account);
                                                    editPassword.revalidate();
                
                                                    JLabel passwordPrompt = new JLabel("Enter new password:");
                                                    editPassword.add(passwordPrompt);
                                                    editPassword.revalidate();
                
                                                    JTextField passwordInput = new JTextField();
                                                    passwordInput.setPreferredSize(new Dimension(200, 30));
                                                    passwordInput.setMaximumSize(passwordInput.getPreferredSize());
                                                    editPassword.add(passwordInput);
                                                    passwordInput.setAlignmentX(Component.LEFT_ALIGNMENT);
                                                    editPassword.revalidate();
                
                                                    JButton cancel = new JButton("Cancel");
                                                    editPassword.add(cancel);
                                                    cancel.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            tempAccounts.clear();
                                                            frame.getContentPane().removeAll();
                                                            frame.repaint();
                                                            frame.getContentPane().add(editedAccount);
                                                            frame.validate();
                                                        }
                                                    });
                                                    editPassword.revalidate();
                
                                                    passwordInput.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            for (int i = 0; i < accounts.size(); i++) {
                                                                if (accounts.get(i).equals(editAccount)) {
                                                                    accounts.get(i).setPassword(passwordInput.getText());
                                                                    JLabel newPrompt = new JLabel("New Account:");
                                                                    editPassword.add(newPrompt);
                                                                    JLabel newAccount = new JLabel(accounts.get(i).getInfo());
                                                                    JButton done = new JButton("Done");
                                                                    done.addActionListener(new ActionListener() {
                                                                        public void actionPerformed(ActionEvent e) {
                                                                            tempAccounts.clear();
                                                                            frame.getContentPane().removeAll();
                                                                            frame.repaint();
                                                                            frame.getContentPane().add(panelMenu);
                                                                            frame.validate();
                                                                        }
                                                                    });
                                                                    cancel.setVisible(false);
                                                                    passwordInput.setVisible(false);
                                                                    passwordPrompt.setVisible(false);
                                                                    editPassword.add(newAccount);
                                                                    editPassword.add(done);
                                                                    editPassword.revalidate();
                                                                    break;
                                                                }
                                                            }   
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        }
                    });

                    JButton cancel = new JButton("Cancel");
                    multipleAccounts.add(cancel);
                    cancel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    cancel.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            tempAccounts.clear();
                            frame.getContentPane().removeAll();
                            frame.repaint();
                            frame.getContentPane().add(panelMenu);
                            frame.validate();
                        }
                    });
                    multipleAccounts.revalidate();
                }
            }
        });
    }
}