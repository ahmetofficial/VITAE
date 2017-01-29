/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('RELATIONSHIPS', {
    active_user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    passive_user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    status_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'STATUS',
        key: 'status_id'
      }
    },
    date: {
      type: DataTypes.TIME,
      allowNull: false,
      defaultValue: sequelize.literal('CURRENT_TIMESTAMP')
    }
  }, {
    tableName: 'RELATIONSHIPS'
  });
};
