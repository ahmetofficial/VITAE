/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('EVENT_REQUESTS', {
    sender_user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    receiver_user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    event_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'EVENTS',
        key: 'event_id'
      }
    },
    request_time: {
      type: DataTypes.TIME,
      allowNull: false,
      defaultValue: sequelize.literal('CURRENT_TIMESTAMP')
    },
    ip: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'EVENT_REQUESTS'
  });
};
