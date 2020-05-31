%% B2) Matlab code for Takeoff Performance

%% No Engine Fail
g=9.81; 
rhosl=1.225;  %sea level density
S = 428.61;
b = 64.86;
AR = b^2/S;
e = 0.8;
K = 1/(pi*AR*e);
Cdo_takeoff = 0.032;
CLmax_takeoff = 1.85; 
Cl_groundroll = 1.0;
h1 = 6.096;     %mean height of wing over ground in roll
phi = 1-(1-1.32*h1/b)/(1.05+7.4*h1/b);

MTOW = 351535*g %maximum takeoff weight
MLW = 251290*g %maximum landing weight

N=2;
runwayslope=0; 
u=0.02; 
sigma = 1;
Vw = 0;  
h = 15.24; %screen height 
rhoh = rhosl*sigma;    
Vsto = ((2.*MTOW/(rhoh*S*CLmax_takeoff))).^0.5;  
CdGE = Cdo_takeoff+phi*K*Cl_groundroll.^2;

%Stog
VLOF = 1.18.*Vsto;  
VgLOF = VLOF-Vw;  
Va = ((VLOF.^2+Vw.*abs(Vw))/2).^0.5;   %average speed
TSL1 = 511000 - (905 * Va) + (0.089 * Va.^2); %thrust
Th1 = TSL1*sigma^0.7;     
D1 = 0.5*rhoh*Va.^2*S*CdGE;
L1 = 0.5*rhoh*Va.^2*S*Cl_groundroll;
Stog = (VgLOF.^2/2)/((g./MTOW).*(N.*Th1-D1-u.*(MTOW-L1)-MTOW.*sin(runwayslope)))

%Stoa 1
V2=1.20*Vsto;  %screen speed
Vc=((V2.^2+VLOF.^2)/2).^0.5;   %average speed of VLOF to V2
TSL2 = 511000 - (905 * Vc) + (0.089 * Vc.^2);  
Th2=TSL2.*sigma.^0.7;     
CL1=(2.*MTOW)./(rhoh.*S.*Vc.^2);
Cd1=Cdo_takeoff+K.*CL1.^2;
D2=0.5.*rhoh.*Vc.^2.*S.*Cd1;
Stoa1=(V2.^2-VLOF.^2)/(2.*(g./MTOW).*(N.*Th2-D2));
tStoa1=(V2-VLOF)/((g./MTOW).*(N.*Th2-D2));
Sgtoa1=Stoa1-Vw*tStoa1

%Stoa 2
TSL3 = 511000 - (905 * V2) + (0.089 * V2.^2); %use V2
Th3=TSL3.*sigma.^0.7;
CL2=(2.*MTOW)./(rhoh.*S.*V2.^2);
Cd2=Cdo_takeoff+(K.*CL2.^2);
gamma=(N.*Th3./MTOW)-(Cd2./CL2); %in radian
Stoa2=h./tan(gamma);
tStoa2=Stoa2./(V2.*cos(gamma));
Sgtoa2=Stoa2-(Vw.*tStoa2)  %ground airbone distance

%SgTO
SgTO=Stog+Sgtoa1+Sgtoa2

%% One Engine Fail and Go

Cdo_takeoff=0.032;
CLgroundroll=1.0;

%Stog 1
VEF = 1.12*Vsto;   %airspeed at engine failure 
VgEF = VEF-Vw;     %ground speed at engine failure
Va = ((VEF^2+Vw*abs(Vw))/2)^0.5;   %average speed from V=0 to VEF
TSL1 = 511000 - (905 * Va) + (0.089 * Va.^2);     %thrust at sea level
Th1 = TSL1*sigma^0.7;      %thrust at altitude
CdGE = Cdo_takeoff + (phi*K*CLgroundroll^2);    
D1 = 0.5*rhoh*Va^2*S*CdGE;     %drag
L1 = 0.5*rhoh*Va^2*S*CLgroundroll;      %lift
Stog1 = (VgEF^2/2)/((g/MTOW)*(N*Th1-D1-u*(MTOW-L1)-MTOW*sin(runwayslope)))

%Stog 2
VLOF = 1.18*Vsto;   %airspeed at lift off 
VgLOF = VLOF-Vw;   %ground speed at lift off
NFail = N-1;     %number of engines after one engine inoperation 
Vb = ((VEF^2+VLOF^2)/2)^0.5;   %average speed from VEF to VLOF
T_SL2 = 511000 - (905 * Vb) + (0.089 * Vb.^2);     %thrust at sea level
T_h2 = T_SL2*sigma^0.7;      %thrust at altitude
D2 = 0.5*rhoh*Vb^2*S*CdGE;
L2 = 0.5*rhoh*Vb^2*S*CLgroundroll;
Stog2 = ((VgLOF^2-VgEF^2)/2)/((g/MTOW)*(NFail*T_h2-D2-u*(MTOW-L2)-MTOW*sin(runwayslope)))

%Stoa 1
V2 = 1.20*Vsto;  %clearance screen speed
Vc = ((V2^2+VLOF^2)/2)^0.5;   %average speed from VLOF to V2
TSL2 = 511000 - (905 * Vc) + (0.089 * Vc.^2);     %thrust at sea level 
Th2 = TSL2*sigma^0.7;     %thrust at altitude
CL1 = (2*MTOW)/(rhoh*S*Vc^2);
Cd1 = Cdo_takeoff + (K*CL1^2);
D3 = 0.5*rhoh*Vc^2*S*Cd1;
Stoa1 = (V2^2-VLOF^2)/(2*(g/MTOW)*(NFail*Th2-D3));
tStoa1 = (V2-VLOF)/((g/MTOW)*(NFail*Th2-D3));
Sgtoa1 = Stoa1-Vw*tStoa1

%Stoa 2
TSL3 = 511000 - (905 * V2) + (0.089 * V2.^2); %use V2
Th3=TSL3*sigma^0.7;
CL2=(2*MTOW)/(rhoh*S*V2^2);
Cd2=Cdo_takeoff+(K*CL2^2);
gamma=asin((NFail*Th3/(MTOW))-(Cd2/CL2)); %in radian
Stoa2=h/tan(gamma);
t_Stoa2=Stoa2/(V2*cos(gamma));
Sgtoa2=Stoa2-(Vw*t_Stoa2)  %ground airbone distance

%SgTO
SgTO=Stog1+Stog2+Sgtoa1+Sgtoa2

%% One Engine Fail and Stop

%Stog 1
VEF = 1.12*Vsto;   %airspeed at engine failure 
VgEF = VEF-Vw;     %ground speed at engine failure
Va = ((VEF^2+Vw*abs(Vw))/2)^0.5;   %average speed from V=0 to VEF
TSL1 = 511000 - (905 * Va) + (0.089 * Va.^2);     %thrust at sea level
Th1 = TSL1*sigma^0.7;     %thrust at altitude
CdGE = Cdo_takeoff+phi*K*CLgroundroll^2;    %
D1 = 0.5*rhoh*Va^2*S*CdGE;     %drag
L1 = 0.5*rhoh*Va^2*S*CLgroundroll;      %lift
Stog1 = (VgEF^2/2)/((g/MTOW)*(N*Th1-D1-u*(MTOW-L1)-MTOW*sin(runwayslope)))

%Sstop
N=0;
u_dec = 0.4;
Cl_dec = 0.2;
Cd_dec = 0.05;
Sdelay = 4 * VgEF;
Cd_GEdec = Cd_dec + phi*K*Cl_dec^2;
D2 = 0.5*rhoh*Va^2*S*Cd_GEdec;    %drag
L2 = 0.5*rhoh*Va^2*S*Cl_dec;      %lift
Stog2 = (-VgEF^2/2)/((g/MTOW)*(0-D2-u_dec*(MTOW-L2)-MTOW*sin(runwayslope)))

%SgTO
SgTO=Stog1+Stog2+Sdelay


