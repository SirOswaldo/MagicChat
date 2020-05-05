Configuracion Basica:

channels:<br>
  channel-name:
    default: true/false #Opcional | defaultValue = false | Canal por defecto al cual todos hablan
    format: "%player_name% :" #Requerido | Formato 
    color: "&f" #Opcional | Color del mensaje
    permission: "custom.node" #Opcional | Solo podran entrar al canal los que tengan este permiso
    autoJoin: true/false #Opcional default = false | Permite que el jugador entre al canal automaticamente
    command: "channeljoin" #Requerido | Permite seleccionar el canal
    aliases: #Opcional | Permite crear alias para el comando
    - "alias1"
    - "alias2"
    bungee: true/false #Opcional | Permite usar el canal como canal a travez de proxy 