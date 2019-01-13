import java.io.*;

public class NapSaqMDI
{
	public static void main(String[] args)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("あなたはお金を持っている。");
		System.out.println("値段と重さがわかる無数の肉塊が目の前に広がっている。");
		System.out.println("所持金内でできるだけ重い肉塊の組み合わせを得る必要がある。");
		System.out.println("----------------------------------");

		int SYOJIKIN = 0;
		while(true)
		{
			try
			{
				System.out.print("何円あるん：");
				String s = in.readLine();
				SYOJIKIN = Integer.parseInt(s);
				break;
			}
			catch(IOException e)
			{
				System.out.println("ERROR:IOException");
			} 
			catch(NumberFormatException e)
			{
				System.out.println("すうじいれて");
			}
		}

		int NIKUNOKAZU = 0;
		while(true)
		{
			try
			{
				System.out.print("肉何個あるん：");
				String s = in.readLine();
				NIKUNOKAZU = Integer.parseInt(s);
				break;
			}
			catch(IOException e)
			{
				System.out.println("ERROR:IOException");
			} 
			catch(NumberFormatException e)
			{
				System.out.println("すうじいれて");
			}
		}

		System.out.println("");

		int[] Y = new int[NIKUNOKAZU];
		int[] G = new int[NIKUNOKAZU];
		for(int i = 0; i <= NIKUNOKAZU - 1; i++)
		{
			System.out.println(i + 1);
			while (true)
			{
				try
				{
					System.out.print("値段：");
					String y = in.readLine();
					Y[i] = Integer.parseInt(y);

					System.out.print("重さ：");
					String g = in.readLine();
					G[i] = Integer.parseInt(g);
					break;
				}
				catch(IOException e)
				{
					System.out.println("ERROR:IOException");
				} 
				catch(NumberFormatException e)
				{
					System.out.println("すうじいれて");
				}
			}
			System.out.println("");
		}
		
		int GENERATION = 0;
		while(true)
		{
			try
			{
				System.out.print("何世代すんの：");
				String s = in.readLine();
				GENERATION = Integer.parseInt(s);
				break;
			}
			catch(IOException e)
			{
				System.out.println("ERROR:IOException");
			} 
			catch(NumberFormatException e)
			{
				System.out.println("すうじいれて");
			}
		}
		
		int KAISU = 0;
		while(true)
		{
			try
			{
				System.out.print("を何回すんの：");
				String s = in.readLine();
				KAISU = Integer.parseInt(s);
				break;
			}
			catch(IOException e)
			{
				System.out.println("ERROR:IOException");
			} 
			catch(NumberFormatException e)
			{
				System.out.println("すうじいれて");
			}
		}

		System.out.print("YEN:");
		System.out.println(java.util.Arrays.toString(Y));
		System.out.print("GRM:");
		System.out.println(java.util.Arrays.toString(G));

		//syoki idenshi wo tukuru
		int[][] E = new int[NIKUNOKAZU][NIKUNOKAZU];
		for(int i = 0; i < NIKUNOKAZU; i++)
		{
			for(int j = 0; j < NIKUNOKAZU; j++)
			{
				double ol = Math.floor(Math.random() * 2);
				int lo = (int)ol;
				E[i][j] = lo;
			}
		}

		// saisyu dasu yatu
		int SAISYU_YEN = 0;
		int SAISYU_YEN_BUFF = 0;
		int SAISYU_GRM = 0;
		int SAISYU_GRM_BUFF = 0;
		String[] SAISYU_SHINAMON = new String [NIKUNOKAZU];
		String[] SAISYU_SHINAMON_BUFF = new String [NIKUNOKAZU];

		//
		//shiteikai shikou
		for(int k = 0; k < KAISU; k++)
		{
			//iden hajime
			for(int g = 0; g < GENERATION; g++)
			{
				int[] Y_sum = new int[NIKUNOKAZU];
				int[] G_sum = new int[NIKUNOKAZU];

				System.out.println("----------------------------------");

				//kimino idenshi wo miruyo
				for(int i = 0; i < NIKUNOKAZU; i++)
				{
					System.out.print(i);
					System.out.print(":");
					for(int j = 0; j < NIKUNOKAZU; j++)
					{
						System.out.print(E[i][j]);
					}
					System.out.println();
				}

				//hyouka
				//i banme no idenshi wo
				for(int i = 0; i < NIKUNOKAZU; i++)
				{
					Y_sum[i] = 0;
					G_sum[i] = 0;
					for(int j = 0; j < NIKUNOKAZU; j++)
					{
						//1 nara syouhin ireru
						if(E[i][j] == 1)
						{
							Y_sum[i] += Y[j];
							G_sum[i] += G[j];
						}
					}
				}
				//tashitano wo syuturyoku
				System.out.print("YEN_sum:");
				System.out.println(java.util.Arrays.toString(Y_sum));
				System.out.print("GRM_sum:");
				System.out.println(java.util.Arrays.toString(G_sum));

				for(int i = 0; i < NIKUNOKAZU; i++)
				{
					//LIMIT no YEN koetara 0 ni suru
					if(Y_sum[i] > SYOJIKIN)
					{
						G_sum[i] = 0;
					}
				}

				//
				//eri-to sentaku
				//
				int top = G_sum[0];
				int[] ELT = new int[NIKUNOKAZU];

				//itiban wo toru
				int f = 0;
				for(int e = 1; e < NIKUNOKAZU; e++)
				{
					if(G_sum[e] > top)
					{
						f = e;
						top = G_sum[e];
						for(int j = 0; j < NIKUNOKAZU; j++)
						{
							ELT[j] = E[e][j];
						}
					}
				}

				//itiban wo syutsuryoku
				System.out.print("YEN_elt:");
				System.out.println(Y_sum[f]);
				System.out.print("GRM_elt:");
				System.out.println(G_sum[f]);

				//itiban tiisainoha 0 ka ina ka
				int min = G_sum[0];
				for(int i = 1; i < NIKUNOKAZU; i++)
				{
					if(G_sum[i] < min)
					{
						min = G_sum[i];
					}
				}

				//0 ga attara
				if(min == 0)
				{
					//LIMIT koeteta yatu ni top wo ireru
					for(int i = 0; i < NIKUNOKAZU; i++)
					{
						if(G_sum[i] == 0)
						{
							for(int j = 0; j < NIKUNOKAZU; j++)
							{
								E[i][j] = ELT[j];
							}
						}
					}
				}
				//0 ga nakattara
				else
				{
					for(int i = 1; i < NIKUNOKAZU; i++)
					{
						if(G_sum[i] == min)
						{
							for(int j = 0; j < NIKUNOKAZU; j++)
							{
								E[i][j] = ELT[j];
							}
						}
					}
				}
				//sentaku no kekka
				System.out.println("//Selection");
				for(int i = 0; i < NIKUNOKAZU; i++)
				{
					System.out.print(i);
					System.out.print(":");
					for(int j = 0; j < NIKUNOKAZU; j++)
					{
						System.out.print(E[i][j]);
					}
					System.out.println();
				}
				for(int i = 0; i < NIKUNOKAZU; i++)
				{
					Y_sum[i] = 0;
					G_sum[i] = 0;
					for(int j = 0; j < NIKUNOKAZU; j++)
					{
						//1 nara syouhin ireru
						if(E[i][j] == 1)
						{
							Y_sum[i] += Y[j];
							G_sum[i] += G[j];
						}
					}
				}
				System.out.print("YEN_sum:");
				System.out.println(java.util.Arrays.toString(Y_sum));
				System.out.print("GRM_sum:");
				System.out.println(java.util.Arrays.toString(G_sum));

				//
				//niten kousa
				//
				for(int x = 0; (NIKUNOKAZU % 2 == 1 && x < NIKUNOKAZU - 1) || (NIKUNOKAZU % 2 == 0 && x < NIKUNOKAZU); x = x + 2)
				{
					double kir1 = Math.floor(Math.random() * NIKUNOKAZU);
					double kir2 = kir1 + Math.floor(Math.random() * (NIKUNOKAZU - kir1));
					int[][] child = new int[2][NIKUNOKAZU];

					for(int i = 0; i < NIKUNOKAZU; i++)
					{
						if(kir1 <= i && i <= kir2)
						{
							child[0][i] = E[x+1][i];
							child[1][i] = E[x][i];
						}
						else
						{
							child[0][i] = E[x][i];
							child[1][i] = E[x+1][i];
						}
					}
					//ireru
					for(int i = 0; i < NIKUNOKAZU; i++)
					{
						E[x][i] = child[0][i];
						E[x+1][i] = child[1][i];
					}
				}
				//kousago no idenshi
				System.out.println("//Crossover");
				for(int i = 0; i < NIKUNOKAZU; i++)
				{
					System.out.print(i);
					System.out.print(":");
					for(int j = 0; j < NIKUNOKAZU; j++)
					{
						System.out.print(E[i][j]);
					}
					System.out.println();
				}

				//
				//dokka ikko no dokka ikko wo totuzenheni
				//
				double a = Math.floor(Math.random()*NIKUNOKAZU);
				int b = (int)a;
				double c = Math.floor(Math.random()*NIKUNOKAZU);
				int d = (int)c;
				E[b][d] = (E[b][d] + 1)%2;
				//totuzenheni shita tokoro wo syuturyoku
				System.out.println("//Mutation");
				System.out.print(b);
				System.out.print(":");
				for(int q = 0; q < NIKUNOKAZU; q++)
				{
					System.out.print(E[b][q]);
				}
				System.out.println();


				//saisyu no syuturyoku
				if(g == GENERATION - 1)
				{
					System.out.println("----------------------------------");
					System.out.println("");
					System.out.println("----------------------------------");
					System.out.println("-----------SYOKI_SETTEI-----------");
					System.out.print("YEN : ");
					System.out.println(java.util.Arrays.toString(Y));
					System.out.print("GRM : ");
					System.out.println(java.util.Arrays.toString(G));
					System.out.print("GENERATION = ");
					System.out.println(GENERATION);
					System.out.print("SYOJIKIN = ");
					System.out.println(SYOJIKIN);
					System.out.println("----------------------------------");
					System.out.println("---------------KEKKA--------------");
					for(int i = 0; i < NIKUNOKAZU; i++)
					{
						System.out.print(i);
						System.out.print(":");
							for(int j = 0; j < NIKUNOKAZU; j++)
							{
								System.out.print(E[i][j]);
							}
						System.out.println();
					}
					for(int i = 0; i < NIKUNOKAZU; i++)
					{
						Y_sum[i] = 0;
						G_sum[i] = 0;
						for(int j = 0; j < NIKUNOKAZU; j++)
						{
							if(E[i][j] == 1)
							{
								Y_sum[i] += Y[j];
								G_sum[i] += G[j];
							}
						}
					}
					System.out.print("YEN_sum:");
					System.out.println(java.util.Arrays.toString(Y_sum));
					System.out.print("GRM_sum:");
					System.out.println(java.util.Arrays.toString(G_sum));
					for(int i = 0; i < NIKUNOKAZU; i++)
					{
						if(Y_sum[i] > SYOJIKIN)
						{
							G_sum[i] = 0;
						}
					}
					int toq = G_sum[0];
					int v = 0;
					for(int e = 1; e < NIKUNOKAZU; e++)
					{
						if(G_sum[e] > toq)
						{
							toq = G_sum[e];
							v = e;
						}
					}

					System.out.println("----------------------------------");
					System.out.println("----------KONKAINO_KOTAE----------");
					System.out.print("HARAU_KANE : ");
					System.out.println(Y_sum[v]);
					SAISYU_YEN_BUFF = Y_sum[v];
					System.out.print("OMOSA_GOUKEI : ");
					System.out.println(G_sum[v]);
					SAISYU_GRM_BUFF = G_sum[v];
					System.out.print("KAUBEKI_MONO : ");
					int ii = 0;
					for(int i = 0; i < NIKUNOKAZU; i++)
					{
						if(E[v][i] == 1)
						{
							String shinamon = "[" + Y[i] + "-/" + G[i] + "g]";
							System.out.print(shinamon);
							SAISYU_SHINAMON_BUFF[ii] = shinamon;
							ii++;
						}
					}
					System.out.println(".");
					System.out.println("----------------------------------");
					System.out.println("----------------------------------");
				}
			}

			if(SAISYU_YEN < SAISYU_YEN_BUFF)
			{
				SAISYU_YEN = 0;
				SAISYU_YEN = SAISYU_YEN_BUFF;
				SAISYU_GRM = 0;
				SAISYU_GRM = SAISYU_GRM_BUFF;
				SAISYU_SHINAMON = new String [NIKUNOKAZU];
				SAISYU_SHINAMON = SAISYU_SHINAMON_BUFF;
			}

			SAISYU_YEN_BUFF = 0;
			SAISYU_GRM_BUFF = 0;
			SAISYU_SHINAMON_BUFF = new String[NIKUNOKAZU];

			if(k == KAISU - 1)
			{
				System.out.println("----------------------------------");
				System.out.println("----------------------------------");
				System.out.println("----------------------------------");
				System.out.println("-----------SYOKI_SETTEI-----------");
				System.out.print("YEN:");
				System.out.println(java.util.Arrays.toString(Y));
				System.out.print("GRM:");
				System.out.println(java.util.Arrays.toString(G));
				System.out.print("YEN_LIMIT = ");
				System.out.println(SYOJIKIN);
				System.out.print("GENERATION = ");
				System.out.println(GENERATION);
				System.out.print("KAISU = ");
				System.out.println(KAISU);
				System.out.println("----------------------------------");
				System.out.println("----------------------------------");
				System.out.println("-----------SAISYU_KEKKA-----------");
				System.out.print("HARAU_KANE : ");
				System.out.println(SAISYU_YEN);
				System.out.print("OMOSA_GOUKEI : ");
				System.out.println(SAISYU_GRM);
				System.out.print("KAUBEKI_MONO : ");
				System.out.println(java.util.Arrays.toString(SAISYU_SHINAMON));
				System.out.println("----------------------------------");
			}
		}
	}
}
