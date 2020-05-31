%% B3) Landing Performance of Boeing 777-300er

%% flow-on landing 
g = 9.81;
MLW = 251290*g;   %max landing weight
CLmax = 1.85

sigma = 1;
rhosl = 1.225;
rhoh = rhosl*sigma;
VsLD = ((2*MLW)/(rhoh*S*CLmax))^0.5;
Vair = 1.30*VsLD;
h = 15.24;
gammaD = 0.05236;    %approach angle
SLA1 = h/tan(gammaD);
Vw = 0;
h1 = 6.096;
phi = 1-(1-1.32*h1/b)/(1.05+7.4*h1/b);

u_decel = 0.4; %coefficicent of friction for braking 
S = 428.61;
b = 64.86;
AR = b^2/S;
e = 0.8;
K = 1/(pi*AR*e);
Cdo_approach = 0.05;    

CLf=1.2;    %flare lift coefficient 
CLa=1;       %apporach lift coefficient
T=0 ;         %thrust, if theres reverse thrust, T=negative 
R=(Vair^2)/(g*(CLf/CLa-1));
V=(VsLD^2+Vair^2)^0.5;
CL=MLW/(0.5*rhoh*V^2*S);
Cd=Cdo_approach+phi*K*CL^2;
D=0.5*rhoh*V^2*S*Cd;
SLA2=R*sin(gammaD/2);

SLA=SLA1+SLA2;
t_LA=h/(Vair*tan(gammaD))+R*sin(gammaD/2)/Vair;
SgLA=SLA-Vw*t_LA

Vg_td=Vair-Vw; %in flawn on: Vtd = Vair
t_LA1=2;  %2sec delay
SgLG1=t_LA1*Vg_td 


V=((Vair^2+Vw*abs(Vw))/2)^0.5;
T = 511000 - (905 * V) + (0.089 * V.^2);
Trev = -0.2*T;
N=2;
runwayslope=0;
Cdodecel=0.075;      %Cdo for deceleration
CLdecel=0.2;    %CL for deceleration
CdGE=Cdodecel+phi*K*CLdecel^2;    %
D=0.5*rhoh*V^2*S*CdGE;     %drag
L=0.5*rhoh*V^2*S*CLdecel ;     %lift
SgLG2=(0-Vg_td^2/2)/((g/MLW)*(N*Trev-D-u_decel*(MLW-L)-MLW*sin(runwayslope)))

SLD=SgLA+SgLG1+SgLG2

%% stall-on landing

g = 9.81;
MLW = 251290*g;   %max landing weight

u_decel=0.4; %coefficicent of friction for braking 
S=428.61;
b=64.86;
AR=b^2/S;
e=0.8;
K=1/(pi*AR*e);
Cdo_approach=0.05;    %Cdo for approach configuration
CLmax=1.85;   %CLmax for approach configuration

sigma=1;
rhosl=1.225;
rhoh=rhosl*sigma;
VsLD=((2*MLW)/(rhoh*S*CLmax))^0.5;
Vair=1.2*VsLD;
h=15.24; %approach height
gammaD=0.05236;    %approach angle 3deg
SLA1=h/tan(gammaD);
Vw=0;
h1=6.096; %mean height of the wing
phi=1-(1-1.32*h1/b)/(1.05+7.4*h1/b);

CLf=1.2;    %flare lift coefficient 
CLa=1;       %apporach lift coefficient
R=(Vair^2)/(g*(CLf/CLa-1));
V=(VsLD^2+Vair^2)^0.5;
T = 0; %thrust, if theres reverse thrust, T=negative 
CL=MLW/(0.5*rhoh*V^2*S);
Cd=Cdo_approach+phi*K*CL^2;
D=0.5*rhoh*V^2*S*Cd;
SLA2=R*sin(gammaD/2); 
SLA3 = (MLW*(VsLD^2-Vair^2))/((2*g)*(0-D));

SLA=SLA1+SLA2+SLA3;
t_LA=h/(Vair*tan(gammaD)) + R*sin(gammaD/2)/Vair + (MLW*(VsLD-Vair))/((g)*(0-D));
SgLA=SLA-Vw*t_LA

Vg_td=VsLD-Vw; %in flawn on: Vtd = Vair
t_LA1=2;  %2sec delay
SgLG1=t_LA1*Vg_td 


V=((VsLD^2+Vw*abs(Vw))/2)^0.5;
T = 511000 - (905 * V) + (0.089 * V.^2);
Trev = -0.2*T;
N=2;
runwayslope=0;
Cdodecel=0.075;      
CLdecel=0.2;    
CdGE=Cdodecel+phi*K*CLdecel^2;    
D=0.5*rhoh*V^2*S*CdGE;    
L=0.5*rhoh*V^2*S*CLdecel;     
SgLG2=(0-Vg_td^2/2)/((g/MLW)*(N*Trev-D-u_decel*(MLW-L)-MLW*sin(runwayslope)))

SLD=SgLA+SgLG1+SgLG2

