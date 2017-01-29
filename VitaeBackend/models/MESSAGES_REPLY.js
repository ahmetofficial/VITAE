/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('MESSAGES_REPLY', {
    conversation_reply_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    reply_text: {
      type: DataTypes.STRING,
      allowNull: false
    },
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    ip: {
      type: DataTypes.STRING,
      allowNull: false
    },
    time: {
      type: DataTypes.TIME,
      allowNull: false,
      defaultValue: sequelize.literal('CURRENT_TIMESTAMP')
    },
    status: {
      type: DataTypes.INTEGER(11),
      allowNull: false
    },
    conversation_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'MESSAGES',
        key: 'conversation_id'
      }
    }
  }, {
    tableName: 'MESSAGES_REPLY'
  });
};
