%% B4) Range-Payload Diagram of Boeing 777-300er

%% Properties
g = 9.81;
rhoSL = 1.225;
S = 428.61;
AR = 9.815;
e = 0.8;
K = 1/(pi*AR*e);
TSFC = 0.0556;    
Fcoms = 9878; 
  
MTOW=396890*g;
Wem=167000*g;
WZF=248110*g; 
Mcru=0.85;
Cdo_cruise=0.02;
hcruise = 12000; 
TempSL=288.15;  

delta=1-2.26*10^-5*hcruise; 
sigma=delta^4.256; 
rhocruise=rhoSL*sigma;
Tempcru=TempSL*delta;
acru=(1.4*287*Tempcru)^0.5;
Vcru_ms=Mcru*acru;  
Vcruise_kmh=Vcru_ms*3.6; 

%% R1
WFres = 0.75*Fcoms*g;
WFclimb = 6900*g;
WFdesLD = 4800*g;
WFdelay = 0;    
WmaxPL = WZF-Wem;
Masspl = WmaxPL/g;

Wfuel = MTOW-WmaxPL-Wem;
WFcru1 = Wfuel-WFres-WFdelay-WFclimb-WFdesLD
Wini1 = Wem+WmaxPL+WFcru1+WFres+WFdesLD+WFdelay %-WFclimb
W = MTOW - WFclimb
Wfin1 = Wini1-WFcru1;
Wbar1 = (Wini1*Wfin1)^0.5;     
CL1 = Wbar1/(0.5*rhocruise*Vcru_ms^2*S);
CD1 = Cdo_cruise+K*CL1^2;

Rcru1 = (1/g)*(1/TSFC)*Vcruise_kmh*(CL1/CD1)*log(Wini1/Wfin1) 
    
%% R2

Wmaxfuel = 170300*g
WPL2 = MTOW-Wmaxfuel-Wem;
Wini2 = Wem+WPL2+Wmaxfuel-WFclimb
W = MTOW - WFclimb
WFcru2 = Wmaxfuel-WFres-WFdelay-WFclimb-WFdesLD;   
Wfin2 = Wini2-WFcru2;
Wbar2 = (Wini2*Wfin2)^0.5;      
CL2 = Wbar2/(0.5*rhocruise*Vcru_ms^2*S);
CD2 = Cdo_cruise+K*CL2^2;

Rcru2=(1/g)*(1/TSFC)*Vcruise_kmh*(CL2/CD2)*log(Wini2/Wfin2) 

%% R3
Wfuel
WPL3 = 0;
Wini3 = Wem+WPL3+Wfuel-WFclimb;
WFcru3 = Wfuel-WFres-WFdelay-WFclimb-WFdesLD  
Wfin3 = Wini3-WFcru3;
Wbar3 = (Wini3*Wfin3)^0.5;       
CL3 = Wbar3/(0.5*rhocruise*Vcru_ms^2*S);
CD3 = Cdo_cruise+K*CL3^2;

Rcru3 = (1/g)*(1/TSFC)*Vcruise_kmh*(CL3/CD3)*log(Wini3/Wfin3)  

%% Figure    
figure
R1 = linspace(0,Rcru1);
W1 = linspace(WmaxPL,WmaxPL);   
plot(R1,W1); 
hold on

R2 = linspace(Rcru1,Rcru2);
W2 = linspace(WmaxPL,WPL2);
plot(R2,W2);
hold on

R3 = linspace(Rcru2,Rcru3);
W3 = linspace(WPL2,0);
plot(R3,W3);

title('Rang-Payload Diagram') 
xlabel('Range, m')
ylabel('Payload, N')
legend('MTOW reached','fuel limit reached','max. ferry range')
ylim([0 9E5])
grid on

   