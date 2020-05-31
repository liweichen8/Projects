%% B1) Matlab Code of Flight Envelope and Climb Performance

%% Climb Performance

MTOW = 351535; %maximum takeoff weight
MLW = 251290; %maximum landing weight
g=9.81;
Wo = MTOW*g; %nomal weight
Tmax = 511000;

rhosl = 1.225;
CDo = 0.032;
b = 64.86;
S = 428.61;
AR = b^2/S;
e = 0.8;
K = 1/(pi*AR*e);

alt = 0:1:35000;
Temp = 59-0.00356*alt;
Press = 2116.*[(Temp+459.7)./518.6].^5.256;
Den = Press./(1718.*(Temp+459.7)); %accurate way to calculate density in different altitude
rho = Den*515.38; %unit convert to kg/m^3
sigma = rho/rhosl;
h = alt.*0.3048; %convert unit to m

Cl_max = 1.85;
Vstall = sqrt((2*Wo)./(rho*S*Cl_max));
Vcru = 1.7*Vstall; %assume the cruise speed is 1.7 times Vstall

Tsl = 511000 - (905 * Vcru) + (0.089 * Vcru.^2); %in terms of V
T = Tsl.*(sigma.^0.7);
Cl_max = -((2.*T)./(2*Wo*K))+sqrt(((2.*T)./(2*Wo*K)).^2+((3*CDo)/K));
MCR = sqrt((2*Wo)./(rho*S)).*(((2*T)./(Wo*sqrt(Cl_max)))-(CDo./Cl_max.^1.5)-(K.*Cl_max.^0.5));
figure(1)
plot(h,MCR)
title('climb rate vs. altitude') 
xlabel('altitude, m')
ylabel('climb rate(MCR), m/s')


%% Flight Envelop

Vstallmax = max(Vstall);
A = 0.5.*rho*S*CDo;
B = (2*K*(Wo)^2)./(rho*S);
Vmax = sqrt((2*T+sqrt((2*T).^2-(4.*A.*B)))./(2.*A));

figure(2)
plot(Vstall,h)
hold on
plot(Vmax, h)
hold on
V=Vstallmax:min(Vmax); %top line
y=max(h);
plot(V,y*ones(size(V)))
title('SLF Flight Envelope') 
xlabel('true air velocity, m/s')
ylabel('altitude, m')
legend('Vstall','Vmax at cruise alititude','Vmax at different altitude')
 
rho = 1.225;
Vstallmin = min(Vstall);
Vslcru = 1.7*Vstallmin;
T = 511000 - (905 * Vslcru) + (0.089 * Vslcru.^2);
A = 0.5.*rho*S*CDo;
B = (2*K*(Wo)^2)./(rho*S);
Vmax = sqrt((2*T+sqrt((2*T).^2-(4.*A.*B)))./(2.*A));
V = Vstallmin:0.1:Vmax;
q = 0.5*rho*V.^2;
theta = (2*T/Wo)-(((q.*S*CDo)/Wo)+((K*Wo)./(q.*S)));
RC = V.*sin(theta);

RCmax = max(RC);
for V1 = Vstallmin:0.1:Vmax;
q = 0.5*rho*V1.^2;
theta = (2*T/Wo)-(((q.*S*CDo)/Wo)+((K*Wo)./(q.*S)));
RC1 = V1.*sin(theta);
if RC1 == RCmax;
    break;
end
end
VFC = V1;
thetaFC = theta;
thetaFC_deg = thetaFC * 180 / pi;

Clmd = sqrt(CDo/K);
CDmd = 2*CDo;
Vmd = sqrt((2*Wo)/(rho*S*Clmd));
Tmd = 0.5*rho*Vmd^2*S*CDmd;
thetamax = (2*T/Wo) - (2*sqrt(K*CDo));
RCmd = Vmd*sin(thetamax);

figure(3)
plot(V,RC)
hold on
Maxmd = linspace(0,Vmd);
Maxangle = linspace(0,RCmd);
plot(Maxmd,Maxangle)
hold on
Maxspeed = linspace(VFC,VFC);
Maxclimb = linspace(0,RCmax);
plot(Maxspeed,Maxclimb)
ylim([0 30])
title('Hodograph') 
xlabel('horizontal velocity, m/s')
ylabel('Rate of Climb (vertical velocity), m/s')
legend('climb rate with different speed','max climb angle','angle for max climb rate')




